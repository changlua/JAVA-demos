package com.example.springsecurity2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthorizationConfig
 * @Author ChangLu
 * @Date 2021/9/20 10:10
 * @Description 认证授权Server端
 */

@Component
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单提交
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    /**
     * appid mayikt secret= 123456
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // appid
                .withClient("test")
                // appsecret
                .secret(passwordEncoder.encode("123456"))
                // 授权码
                .authorizedGrantTypes("authorization_code")
                // 作用域
                .scopes("all")
                // 资源的id
                .resourceIds("mayikt_resource")
                // 回调地址
                .redirectUris("http://www.mayikt.com/callback");

    }

}