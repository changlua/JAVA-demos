package com.changlu.config;

import com.changlu.service.UserService;
import com.changlu.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:57 PM
 */
@Configuration
@ConditionalOnClass(UserService.class) //当类路径下有该指定类配置才生效
public class CustomConfigurationClass {

    @Bean
    public UserService custumeService() {
        System.out.println("custome service...");
        return new UserServiceImpl();
    }

}
