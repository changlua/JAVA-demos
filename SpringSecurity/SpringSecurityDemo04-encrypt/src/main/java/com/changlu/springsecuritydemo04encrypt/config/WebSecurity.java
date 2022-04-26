package com.changlu.springsecuritydemo04encrypt.config;

import com.changlu.springsecuritydemo04encrypt.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebSecurity
 * @Author ChangLu
 * @Date 3/24/2022 5:36 PM
 * @Description 自定义security配置器
 */
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {


//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService(){
//        final InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        //方式一：动态方式
////        userDetailsManager.createUser(User.withUsername("changlu").password("{noop}123").roles("admin","user").build());
////        userDetailsManager.createUser(User.withUsername("admin").password("{bcrypt}$2a$10$Fsv9EcqNxlJH8ZMMLvUHfOfwc91DTeyUQVDsYSRAji0DpOv6/jFte").roles("admin","user").build());
//        //方式二：工厂注入BCryptPasswordEncoder+密码直接存加密之后的
//        userDetailsManager.createUser(User.withUsername("admin").password("$2a$10$Fsv9EcqNxlJH8ZMMLvUHfOfwc91DTeyUQVDsYSRAji0DpOv6/jFte").roles("admin","user").build());
//        return userDetailsManager;
//    }

    @Autowired
    private MyUserDetailService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().csrf().disable();
    }

}