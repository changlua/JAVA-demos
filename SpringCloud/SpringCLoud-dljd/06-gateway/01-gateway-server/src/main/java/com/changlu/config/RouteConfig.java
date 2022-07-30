package com.changlu.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: changlu
 * @Date: 1:15 PM
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //路由
                .route("baidu_route", r -> r.path("/s").uri("https://www.baidu.com/"))
                .build();
    }

}
