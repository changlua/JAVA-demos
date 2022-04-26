package com.changlu.springsecuritydemo3web.config;

import com.changlu.springsecuritydemo3web.security.filter.KaptchaFilter;
import com.changlu.springsecuritydemo3web.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName WebSecurity
 * @Author ChangLu
 * @Date 3/23/2022 3:10 PM
 * @Description 自定义Security配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        1、自定义内存形式创建用户
//        final InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("changlu").password("{noop}123").roles("admin","user").build());
        //2、自定义认证源来进行用户信息的获取
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/getVerifyCode").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login.html")  //要想走自己自定义登录页面的话，那么就必须在这里配置
//                        .loginProcessingUrl("/doLogin")
//                        .usernameParameter("uname")
//                        .passwordParameter("passwd")
//                    .defaultSuccessUrl("/logout.html",true) //登录成功后重定向到logout.html
                    .failureUrl("/login.html")  //redirct，异常信息保存在session作用域中
                .and()
                    .logout()
                        .logoutUrl("/logout")  //登录接口默认为/logout
                        .logoutSuccessUrl("/login.html")  //注销成功跳转url
                .and()
                .csrf().disable();

        //添加自定义验证码过滤器到校验用户名、密码过滤器前（其实at覆盖也可以，因为KaptchaFilter本身是继承UsernamePasswordAuthenticationFilter，
        // 并且在KaptchaFilter的attemptAuthentication中验证码通过就super.attemptAuthentication()了）
        http.addFilterBefore(kaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    //配置KaptchaFilter
    @Bean
    public KaptchaFilter kaptchaFilter() throws Exception {
        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        kaptchaFilter.setFilterProcessesUrl("/doLogin");//一定要指定请求路径，否则不会走过滤器
        //注意注意：若是自定义的这个filter不设置用户名、密码名称，其会走到katpcherfilter继承父类的认证中
        //通过debug发现，一旦kaptchaFilter其父类的用户名、密码认证完毕之后，后面的UsernamePasswordAuthenticationFilter不会再走一遍认证！！！
        //那么原本configure(http)那就可以不进行配置了用户名与密码名称了
        kaptchaFilter.setUsernameParameter("uname");
        kaptchaFilter.setPasswordParameter("passwd");
        kaptchaFilter.setAuthenticationManager(authenticationManager());//设置自定义认证管理器
        kaptchaFilter.setKaptchaParameter("kaptcha");//自定义获取验证码参数
        //设置认证成功处理器
        kaptchaFilter.setAuthenticationSuccessHandler((req,resp,auth)->{
            resp.sendRedirect("/logout.html");
        });
        //设置认证失败处理器，失败直接返回登录页面（若是该filter后面的过滤器报错也会走这边）
        kaptchaFilter.setAuthenticationFailureHandler((req,resp,ex)->{
            System.out.println("kaptcha ex:"+ ex.getMessage());
            resp.sendRedirect("/login.html");
        });
        return kaptchaFilter;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}