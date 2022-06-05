package com.changlu.client.netty;

import com.changlu.client.RPCClient;
import com.changlu.client.handler.RpcResponseMessageHandler;
import com.changlu.common.RPCRequest;
import com.changlu.common.RPCResponse;
import com.changlu.register.ZkServiceRegister;
import com.changlu.register.balance.LoadBalance;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;


/**
 * @ClassName NettyRPCClient
 * @Author ChangLu
 * @Date 5/31/2022 5:50 PM
 * @Description 基于netty的RPC调用客户端
 */
@Slf4j
public class NettyRPCClient implements RPCClient {
    private static final EventLoopGroup eventLoopGroup;
    private static final Bootstrap bootstrap;
    private String host;
    private Integer port;
    private LoadBalance loadBalance;//负载均衡策略

    public NettyRPCClient() {
    }

    public NettyRPCClient(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    public NettyRPCClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    @Override
    public Object sendRequest(RPCRequest request) {
        //从注册中心获取到对应服务名的地址
        ZkServiceRegister register = new ZkServiceRegister(loadBalance);
        InetSocketAddress address = register.serviceDiscovery("rpc");
        System.out.println("发现服务rpc的地址：" + address.getHostName() + ":" + address.getPort());
        host = address.getHostName();
        port = address.getPort();
        //进行远程调用执行
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            //---线程通信借助pomise+唯一id---
            DefaultPromise<Object> promise = new DefaultPromise<>(channel.eventLoop());
            RpcResponseMessageHandler.promiseMap.put(request.getSeqId(), promise);
            //发送数据
            channel.writeAndFlush(request);
            //进行阻塞等待
            promise.await();
            //---阻塞结束：表示RPC远程调用结束---
            if (promise.isSuccess()) {
                return promise.getNow();
            }
            //若是失败抛出RPC服务端提供的异常（对应服务端的异常对象）
            throw new RuntimeException((Exception) promise.getNow());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            //client结束后进行优雅关闭
//            eventLoopGroup.shutdownGracefully();
        }
    }
}
