package com.changlu.springsecuritydemo3web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassName UserController
 * @Author ChangLu
 * @Date 3/23/2022 3:42 PM
 * @Description 用户控制器
 */
@Controller
public class UserController {

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Authentication getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}