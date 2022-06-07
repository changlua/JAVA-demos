package com.changlu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @ClassName DubboProducerApplication
 * @Author ChangLu
 * @Date 6/7/2022 2:52 PM
 * @Description 启动器
 */
@EnableAutoConfiguration
public class DubboProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProducerApplication.class, args);
    }
}
