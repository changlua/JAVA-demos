package com.changlu.courselist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.changlu.courselist.mapper")
@EnableFeignClients
@EnableCircuitBreaker    //打开断路器
public class CoursePriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursePriceApplication.class, args);
    }

}
