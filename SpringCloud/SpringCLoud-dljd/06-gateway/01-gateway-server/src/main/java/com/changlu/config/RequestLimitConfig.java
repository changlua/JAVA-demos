package com.changlu.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;


/**
 * @Description: 请求限流配置类
 * @Author: changlu
 * @Date: 10:34 AM
 */
@Configuration
public class RequestLimitConfig {

    //针对某一个ip地址来进行限流（例如：localhost）
    @Bean(name = "ipKeyResolver")
//    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getHost().getHostString());
    }

    //针对某一个接口uri来进行限流（例如：/doLogin）
    @Bean
    @Primary
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }



}
