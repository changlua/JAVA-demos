package com.changlu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 网关服务启动器
 * @Author: changlu
 * @Date: 8:11 PM
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
