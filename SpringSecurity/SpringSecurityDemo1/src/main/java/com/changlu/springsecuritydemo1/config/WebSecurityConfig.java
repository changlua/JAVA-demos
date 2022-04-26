package com.changlu.springsecuritydemo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebSecurityConfig
 * @Author ChangLu
 * @Date 3/22/2022 3:59 PM
 * @Description Security配置类
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  //获取url注册器对象
                .antMatchers("/index").permitAll()  //代表放行该资源,该资源为公共资源 无需认证和授权可以直接访问
                .anyRequest().authenticated() //其他所有请求,必须认证之后才能访问
                .and().formLogin();   //代表开启表单认证
    }
}