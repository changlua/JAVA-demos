package com.changlu.springsecuritydemo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Author ChangLu
 * @Date 3/22/2022 4:12 PM
 * @Description 登录控制器
 */
@Controller
public class LoginController {

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

}