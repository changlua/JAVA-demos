package com.changlu.springsecurity3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @ClassName ResourceConfig
 * @Author ChangLu
 * @Date 2021/9/20 11:06
 * @Description TODO
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${changlu.appid}")
    private String changluAppId;
    @Value("${changlu.appsecret}")
    private String changluAppSecret;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 当访问该服务中的某个接口时都会走这个方法，其中就会去授权服务器进行鉴权：
     *  http://localhost:8080/oauth/check_token？token=71dad84a-b64b-4cf3-91ec-0248298dd9bd
     *  若是检验能够通过则能够进行访问该服务下的接口
     * @return
     */
    @Primary
    @Bean
    public RemoteTokenServices remoteTokenServices() {
        final RemoteTokenServices tokenServices = new RemoteTokenServices();
        //设置授权服务器check_token端点完整地址，根据授权服务端真实ip地址填写
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        //设置客户端id与secret(应当与授权服务端的完全一致)，注意：client_secret值不能使用passwordEncoder加密！
        tokenServices.setClientId(changluAppId);
        tokenServices.setClientSecret(changluAppSecret);
        return tokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //设置创建session策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        //@formatter:off
        //所有请求必须授权
        http.authorizeRequests()
                .anyRequest().authenticated();
        //@formatter:on
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //设置当前服务的资源id，同样需要与授权服务器端要完全一致
        resources.resourceId("mayikt_resource").stateless(true);
    }
}