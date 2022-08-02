package com.changlu.sentinelbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelBookserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelBookserviceApplication.class, args);
    }

}
