package com.changlu.springsecuritydemo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName CommonController
 * @Author ChangLu
 * @Date 3/23/2022 11:01 AM
 * @Description 测试控制器
 */
@Controller
public class CommonController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/error.html")
    public String error() {
        return "error";
    }

    @RequestMapping("/logout.html")
    public String logout1() {
        return "logout";
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout2() {
        return "logout2:注销成功！";
    }

}