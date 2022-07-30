package com.changlu.config;


import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:38 PM
 */
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            System.out.println("当前进入到CheckAuthRoutePredicateFactory：" + config.getName());
            return config.getName().equals("changlu");
        };
    }

    @Data
    static class Config {
        private String name;
    }
}
