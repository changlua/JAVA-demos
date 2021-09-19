package com.changlu.springsecurityexer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.changlu.springsecurityexer.mapper")
public class SpringsecurityexerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityexerApplication.class, args);
    }

}
