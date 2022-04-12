package com.changlu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.changlu.common.mapper"})
public class ClSpringbootExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClSpringbootExcelApplication.class, args);
    }

}
