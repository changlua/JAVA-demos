package com.changlu.springsecurityexer.config;

import com.changlu.springsecurityexer.entity.PermissionEntity;
import com.changlu.springsecurityexer.mapper.PermissionMapper;
import com.changlu.springsecurityexer.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SecurityConfig
 * @Author ChangLu
 * @Date 2021/9/19 15:32
 * @Description TODO
 */
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 新增授权账号方法，一旦进行登陆就会走userDetailsService，来进行DB查询出对应用户及其权限注册到spring security
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //动态增加的形式
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            //登陆密码的编码
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            //等待userDetailsService结束之后，将登陆时的密码及db中查询出来的密码作为参数传递
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String rawPass = MD5Util.encode((String) rawPassword);
                boolean result = rawPass.equals(encodedPassword);
                return result;
            }
        });
    }

    /**
     * 动态从数据库中查询出所有的接口及对应的权限名
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //查询出所有的角色
        List<PermissionEntity> permissionList = permissionMapper.findAllPermission();
        //获取到authorizeRequests来对url接口及接口名进行注册
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                authorizeRequests = http.authorizeRequests();
        permissionList.forEach((permission)->{
            authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
        });
        authorizeRequests.antMatchers("/login").permitAll()
                //使用form表单登陆
                .antMatchers("/**").fullyAuthenticated().and().formLogin()
                // 设置自定义登录页面
                .loginPage("/login").and().csrf().disable();
    }


}