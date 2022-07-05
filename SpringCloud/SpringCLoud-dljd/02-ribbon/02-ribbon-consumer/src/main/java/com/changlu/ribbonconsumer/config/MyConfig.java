package com.changlu.ribbonconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 个人配置类
 * @Author: changlu
 * @Date: 5:03 PM
        */
@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced   //使用负载均衡器
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //手动注入一个负载均衡器
    @Bean
    public IRule loadBalanced() {
        return new RandomRule();
    }

}
