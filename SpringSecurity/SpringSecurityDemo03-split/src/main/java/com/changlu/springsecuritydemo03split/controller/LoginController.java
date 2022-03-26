package com.changlu.springsecuritydemo03split.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName LoginController
 * @Author ChangLu
 * @Date 3/23/2022 4:43 PM
 * @Description 登录控制器
 */
@Controller
public class LoginController {

    //用于进行校验
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

}