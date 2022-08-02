package com.changlu.sentineluserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feign扫描
public class SentinelUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelUserserviceApplication.class, args);
    }

}
