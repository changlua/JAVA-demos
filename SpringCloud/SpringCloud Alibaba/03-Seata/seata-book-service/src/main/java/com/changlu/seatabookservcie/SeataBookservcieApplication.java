package com.changlu.seatabookservcie;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.changlu.seatabookservcie.mapper")
@EnableDiscoveryClient
@EnableAutoDataSourceProxy //开启seata事务配置
public class SeataBookservcieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataBookservcieApplication.class, args);
    }

}
