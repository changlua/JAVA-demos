package com.changlu.client;


import com.changlu.client.netty.NettyRPCClient;
import com.changlu.register.balance.RoundLoadBalance;
import com.changlu.service.BlogService;
import com.changlu.service.UserService;

/**
 * @ClassName MyRPCClient
 * @Author ChangLu
 * @Date 5/31/2022 2:17 PM
 * @Description RPCClient测试
 */
public class MyRPCClient {

    public static void main(String[] args) {
        RPCClientProxy rpcClientProxy = new RPCClientProxy(
                new NettyRPCClient(new RoundLoadBalance())//选择指定的策略
        );//不指定IP地址及端口号，就通过使用服务发现来获取ip地址
//        for (int i = 0; i < 5; i++) {
        try {
            //1、调用用户业务类
            UserService userService = rpcClientProxy.getRPCProxy(UserService.class);
            System.out.println(userService.getUserByUserId(123));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2、调用博客业务类
        try {
            BlogService blogService = rpcClientProxy.getRPCProxy(BlogService.class);
            System.out.println(blogService.getBlogById(123));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
    }

}
