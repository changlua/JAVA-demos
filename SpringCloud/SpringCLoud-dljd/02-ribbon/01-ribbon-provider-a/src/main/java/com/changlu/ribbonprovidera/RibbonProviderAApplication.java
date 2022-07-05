package com.changlu.ribbonprovidera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RibbonProviderAApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonProviderAApplication.class, args);
    }

}
