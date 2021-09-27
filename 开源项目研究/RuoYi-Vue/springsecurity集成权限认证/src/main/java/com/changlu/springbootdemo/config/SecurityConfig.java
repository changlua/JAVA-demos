package com.changlu.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName SecurityConfig
 * @Author ChangLu
 * @Date 2021/9/25 17:27
 * @Description SpringSecurity配置类
 */

//prePostEnabled = true => 会解锁 @PreAuthorize 和 @PostAuthorize 两个注解：分别在方法执行前、后进行验证
//securedEnabled = true => 开启@Secured 注解过滤权限
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 强散列哈希加密实现（加密采用随机盐加密，并且盐直接会存储在密码中）
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}