package com.changlu.myhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.changlu.myhystrix.feign")
public class MyhystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhystrixApplication.class, args);
    }

}
