package com.changlu.springsecuritydemo2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author ChangLu
 * @Date 3/22/2022 7:44 PM
 * @Description 用户控制器
 */
@RestController
public class UserController {


    @RequestMapping("/getUser")
    public Authentication getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println("身份信息："+user);
        System.out.println("权限信息："+authentication.getAuthorities());//每个角色前自动加上ROLE_
        System.out.println("凭证：" + authentication.getCredentials());//认证成功后就会将凭证删除，自然为null
        //若是想要子线程能够拿到父线程的Authentication信息，那么就需要在启动运行时配置：-Dspring.security.strategy=MODE_INHERITABLETHREADLOCAL
        //本质就是在项目初始化时使用InheritableThreadLocalSecurityContextHolderStrategy()策略
        new Thread(()->{
            System.out.println("子线程：" + SecurityContextHolder.getContext().getAuthentication());
        }).start();
        return authentication;
    }


}