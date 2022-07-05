package com.changlu.ribbonproviderb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RibbonProviderBApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonProviderBApplication.class, args);
    }

}
