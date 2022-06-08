package com.changlu.server.netty;

import com.changlu.register.ZkServiceRegister;
import com.changlu.server.RPCServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

import java.net.InetSocketAddress;

/**
 * @ClassName NettyRPCServer
 * @Author ChangLu
 * @Date 5/31/2022 4:27 PM
 * @Description 基于Netty的RPC服务端实现
 */
@AllArgsConstructor
public class NettyRPCServer implements RPCServer {

    private ServiceProvider serviceProvider;

    @Override
    public void start(int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup(2);
        try {
            ChannelFuture channelFuture = new ServerBootstrap()
                    .group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer(serviceProvider)) //初始化handler处理器
                    .bind(new InetSocketAddress(port)).sync();
            channelFuture.channel();
            System.out.println("服务器启动成功,端口为：" + port);
            //进行服务注册
            ZkServiceRegister zkServiceRegister = new ZkServiceRegister();
            zkServiceRegister.register("rpc", new InetSocketAddress("localhost", port));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
