package com.changlu.springbootdemoredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootDemoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoRedisApplication.class, args);
    }

}
