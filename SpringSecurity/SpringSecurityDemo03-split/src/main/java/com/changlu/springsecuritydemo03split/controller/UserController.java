package com.changlu.springsecuritydemo03split.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author ChangLu
 * @Date 3/23/2022 6:13 PM
 * @Description 用户控制器
 */
@RestController
public class UserController {

    @GetMapping("/getUserInfo")
    public Authentication getUserInfo(){
       return SecurityContextHolder.getContext().getAuthentication();
    }

}