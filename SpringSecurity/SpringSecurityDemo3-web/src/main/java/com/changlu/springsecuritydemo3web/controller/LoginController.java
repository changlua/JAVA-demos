package com.changlu.springsecuritydemo3web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName LoginController
 * @Author ChangLu
 * @Date 3/23/2022 3:08 PM
 * @Description 登录控制器
 */
@Controller
public class LoginController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello!";
    }

}