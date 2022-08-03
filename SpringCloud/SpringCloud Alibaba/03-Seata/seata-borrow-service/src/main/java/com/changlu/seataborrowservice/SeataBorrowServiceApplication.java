package com.changlu.seataborrowservice;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.changlu.seataborrowservice.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoDataSourceProxy //开启seata事务配置
public class SeataBorrowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataBorrowServiceApplication.class, args);
    }

}
