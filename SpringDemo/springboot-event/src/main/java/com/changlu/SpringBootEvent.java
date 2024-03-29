package com.changlu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changlu
 * @version 1.0
 * @description SpringBoot启动类
 * @date 2024/03/28 15:33
 */
@SpringBootApplication
public class SpringBootEvent {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootEvent.class);
//        springApplication.addListeners(new ApplicationEnvironmentPreparedEventListener());
//        springApplication.addListeners(new ApplicationFailedEventListener());
//        springApplication.addListeners(new ApplicationPreparedEventListener());
//        springApplication.addListeners(new ApplicationReadyEventListener());
//        springApplication.addListeners(new ApplicationStartedEventListener());
//        springApplication.addListeners(new ApplicationStartingEventListener());
        springApplication.run(args);
    }
}
