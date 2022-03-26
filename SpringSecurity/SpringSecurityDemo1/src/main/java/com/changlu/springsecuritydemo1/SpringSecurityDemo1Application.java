package com.changlu.springsecuritydemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringSecurityDemo1Application {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(SpringSecurityDemo1Application.class, args);
        System.out.println(111);
    }

}
