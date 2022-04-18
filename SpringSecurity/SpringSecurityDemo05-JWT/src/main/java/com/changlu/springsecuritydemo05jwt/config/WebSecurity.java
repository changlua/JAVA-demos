package com.changlu.springsecuritydemo05jwt.config;

import com.changlu.springsecuritydemo05jwt.security.filter.JwtAuthenticationTokenFilter;
import com.changlu.springsecuritydemo05jwt.security.handler.AuthenticationEntryPointHandler;
import com.changlu.springsecuritydemo05jwt.security.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @ClassName WebSecurity
 * @Author ChangLu
 * @Date 3/25/2022 11:00 AM
 * @Description 自定义security配置
 */
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//自定义认证数据源

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;//自定义认证处理器

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;//处理未认证时执行处理器
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;//处理为授权时执行处理器
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;//处理注销成功执行器

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);//配置自定义认证数据源
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()  //放行登录接口
                .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPointHandler)  //认证异常，默认返回403的HTTP请求
                        .accessDeniedHandler(myAccessDeniedHandler)  //授权异常，默认返回403的HTTP请求
                .and()
                    .logout().
                        logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)  //自定义注销成功处理器
                .and()
                    .csrf().disable()
                    //不通过Session获取SecurityContext（当前已经是前后端分离）
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //添加JWT认证过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, LogoutFilter.class);

        //支持跨域
        http.cors();
    }

    //让AuthenicationManager从工厂中暴露出来
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }
}