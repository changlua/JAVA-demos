package com.changlu.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurakeServerApplication
 * @Author ChangLu
 * @Date 2021/10/4 13:42
 * @Description 服务中心服务
 */
@EnableEurekaServer   //Eureka Server服务端
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}