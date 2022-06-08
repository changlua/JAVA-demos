package com.changlu.client.netty;

import com.changlu.client.RPCClient;
import com.changlu.client.handler.RpcResponseMessageHandler;
import com.changlu.common.RPCRequestMessage;
import com.changlu.register.ZkServiceRegister;
import com.changlu.register.balance.LoadBalance;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName NettyRPCClient
 * @Author ChangLu
 * @Date 5/31/2022 5:50 PM
 * @Description 基于netty的RPC调用客户端
 */
@Slf4j
public class NettyRPCClient implements RPCClient {
    private static EventLoopGroup eventLoopGroup = null;
    private static Bootstrap bootstrap = null;
    //单独的一个channel
    private static volatile Channel channel = null;
    private static Object lock = new Object();
    private String host;
    private Integer port;
    private LoadBalance loadBalance;//负载均衡策略

    public NettyRPCClient() {
    }

    static{
        //初始化工作组
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    public NettyRPCClient(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    public NettyRPCClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object sendRequest(RPCRequestMessage request) {
        //从注册中心获取到对应服务名的地址
        ZkServiceRegister register = new ZkServiceRegister(loadBalance);
        try {
            InetSocketAddress address = register.serviceDiscovery("rpc");
            System.out.println("发现服务rpc的地址：" + address.getHostName() + ":" + address.getPort());
            //获取一个channel
            Channel channel = getChannel(address.getHostName(), address.getPort());
            //测试心跳检测：停30秒来测试
//            System.out.println("---睡觉中---");
//            Thread.sleep(30 * 1000);
//            System.out.println("---唤醒---");
            //---线程通信借助pomise+唯一id---
            DefaultPromise<Object> promise = new DefaultPromise<>(channel.eventLoop());
            RpcResponseMessageHandler.promiseMap.put(request.getSeqId(), promise);
            //发送数据并且编写一个回调函数
            ChannelFuture cf = channel.writeAndFlush(request);
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    //写操作完成，并没有错误发生
                    if (future.isSuccess()){
                        System.out.println("successful");
                    }else{
                        //记录错误
                        System.out.println("error");
                        future.cause().printStackTrace();
                    }
                }
            });
            //进行阻塞等待（阻塞10秒）
            promise.await(10, TimeUnit.SECONDS);
            //---阻塞结束：表示RPC远程调用结束---
            if (promise.isSuccess()) {
                return promise.getNow();
            }
            if (promise.getNow() != null && promise.getNow() instanceof Exception) {
                //若是失败抛出RPC服务端提供的异常（对应服务端的异常对象）
                throw new RuntimeException((Exception) promise.getNow());
            }
            throw new RuntimeException("未得到响应，或许需要进行重试...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 单例获取channel
     * @return
     */
    public static Channel getChannel(String host, Integer port) {
        if (channel != null) {
            System.out.println("使用当前channel：" + channel);
            return channel;
        }
        synchronized (lock) {
            if (channel != null) {
                System.out.println("使用当前channel：" + channel);
                return channel;
            }
            initChannel(host, port);
        }
        return channel;
    }

    //获取一个channel
    public static void initChannel(String host, Integer port) {
        try {
            //建立连接
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channel = channelFuture.channel();
            System.out.println("channel连接成功：" + channel);
            //优雅关闭
            channel.closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    System.out.println("客户端关闭连接!");
                    //进行优雅关闭
//                    eventLoopGroup.shutdownGracefully();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭channel
     */
    public static void closeChannel() {
        if (channel != null) {
            System.out.println(String.format("channel连接已关闭：%s", channel));
            channel.close();
            channel = null;
        }
    }
}
