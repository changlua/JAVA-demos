package com.changlu.springsecuritydemo03split.config;

import com.changlu.springsecuritydemo03split.security.filter.LoginFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WebSecurity
 * @Author ChangLu
 * @Date 3/23/2022 4:39 PM
 * @Description 自定义Security配置
 */
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;


    //将LoginFilter暴露在工厂中：方便对其进行配置
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManagerBean());//一定要在过滤器中显示设置自定义的认证管理器（否则报错authenticationManager must be specified）
        //设置登录接口的各项参数：url地址、用户名、密码
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setUsernameParameter("uname");
        loginFilter.setPasswordParameter("passwd");
        //登录成功执行器
        loginFilter.setAuthenticationSuccessHandler((req,resp,auth)->{
            Map<String,Object> result = new HashMap<>(3);
            result.put("msg","登录成功");
            result.put("code","200");
            result.put("authenication", auth);
            String json = new ObjectMapper().writeValueAsString(result);
            resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            resp.getWriter().write(json);
        });
        loginFilter.setAuthenticationFailureHandler((req,resp,ex)->{
            Map<String,String> result = new HashMap<>(3);
            result.put("msg","登录失败");
            result.put("code","500");
            result.put("ex", ex.getMessage());
            String json = new ObjectMapper().writeValueAsString(result);
            resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            resp.getWriter().write(json);
        });
        return loginFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);//注入自定义认证数据源
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/getVerifyCode").permitAll()  //放行验证码
                .anyRequest().authenticated()  //所有请求必须认证
                .and()
                    .formLogin() //前后端分离依旧走的是FormLogin，只不过使用的是自己定义的filter（继承UsernamePasswordAuthenticationFilter）
                .and()
                    //对于直接访问保护资源时出现的异常回调，默认是返回的登录页面；若是这里设置handler，则会返回自定义内容
                    .exceptionHandling()
                        .authenticationEntryPoint((req,resp,ex)->{
                            Map<String,String> result = new HashMap<>(3);
                            result.put("msg","请认证之后再访问");
                            result.put("code","500");
                            result.put("ex", ex.getMessage());
                            String json = new ObjectMapper().writeValueAsString(result);
                            resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                            resp.getWriter().write(json);
                        })
                .and()
                .logout()
                    //定义logout的url（默认就是/logout）
                    .logoutUrl("/logout")
                    //定义logout的路径及方法（默认是/logout，Get）
                    .logoutRequestMatcher(new OrRequestMatcher(
                            new AntPathRequestMatcher("/logout", HttpMethod.DELETE.name()),
                            new AntPathRequestMatcher("/logout", HttpMethod.GET.name())
                    ))
                    //定义logout的成功执行器，进行JSON字符串响应
                    .logoutSuccessHandler((req,resp,auth)->{
                        Map<String,String> result = new HashMap<>(3);
                        result.put("msg","注销成功");
                        result.put("code","200");
                        if (!ObjectUtils.isEmpty(auth)){
                            result.put("注销用户", auth.getName());
                        }
                        String json = new ObjectMapper().writeValueAsString(result);
                        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                        resp.getWriter().write(json);
                    })
                .and()
                    .csrf().disable();

        //当前使用at：自定义的登录filter来替换UsernamePasswordAuthenticationFilter
        // at: 用来某个 filter 替换过滤器链中哪个 filter
        // before: 放在过滤器链中哪个 filter 之前
        // after: 放在过滤器链中那个 filter 之后
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}