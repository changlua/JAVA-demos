package com.changlu.provider;

import com.changlu.api.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Application
 * @Author ChangLu
 * @Date 6/5/2022 8:47 PM
 * @Description 生产者
 */
public class Application {

    private static String zookeeperHost = System.getProperty("zookeeper.address", "192.168.10.40");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) throws InterruptedException {
        ServiceConfig<UserService> service = new ServiceConfig<>();
        //当前服务的应用名
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        //指定注册地址
        service.setRegistry(new RegistryConfig(
                "zookeeper://" + zookeeperHost + ":" + zookeeperPort)
        );
        //设置相应服务的接口以及对应实例
        service.setInterface(UserService.class);
        service.setRef(new UserServiceImpl());
        //进行一个注册
        service.export();

        //阻塞
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
