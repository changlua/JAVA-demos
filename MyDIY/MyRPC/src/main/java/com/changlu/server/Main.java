package com.changlu.server;

import com.changlu.server.netty.NettyRPCServer;
import com.changlu.server.netty.ServiceProvider;
import com.changlu.service.impl.BlogServiceImpl;
import com.changlu.service.impl.UserServiceImpl;

import java.util.HashMap;

/**
 * @ClassName Main
 * @Author ChangLu
 * @Date 5/31/2022 3:44 PM
 * @Description 主测试类
 */
public class Main {
    public static void main(String[] args) {
        //1、存储接口及业务实现类
        HashMap<String,Object> serviceProviders = new HashMap<>(2);
        ServiceProvider serviceProvider = new ServiceProvider(serviceProviders);
        serviceProvider.provideServiceInterface(new BlogServiceImpl());
        serviceProvider.provideServiceInterface(new UserServiceImpl());
        //2、创建基于Netty的RPC服务器
        NettyRPCServer nettyRPCServer = new NettyRPCServer(serviceProvider);
//        nettyRPCServer.start(8899);
//        nettyRPCServer.start(8900);
        nettyRPCServer.start(8902);
    }
}
