package com.changlu.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class, args);
    }

}
