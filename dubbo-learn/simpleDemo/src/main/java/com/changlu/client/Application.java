package com.changlu.client;

import com.changlu.api.UserService;
import com.changlu.pojo.User;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @ClassName Application
 * @Author ChangLu
 * @Date 6/5/2022 8:44 PM
 * @Description 客户端应用
 */
public class Application {

    private static String zookeeperHost = System.getProperty("zookeeper.address", "192.168.10.40");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) {
        ReferenceConfig<UserService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig(
                "zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        reference.setInterface(UserService.class);
        //获取到一个代理类
        UserService service = reference.get();
        //进行远程调用
        User user = service.getUserById(123);
        System.out.println(user);
    }

}
