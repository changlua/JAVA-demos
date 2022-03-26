package com.changlu.springsecuritydemo05jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.changlu.springsecuritydemo05jwt.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)   //开启授权
public class SpringSecurityDemo05JwtApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringSecurityDemo05JwtApplication.class, args);
        System.out.println(run);
    }

}
