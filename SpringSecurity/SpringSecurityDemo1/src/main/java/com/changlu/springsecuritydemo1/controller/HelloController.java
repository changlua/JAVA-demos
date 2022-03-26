package com.changlu.springsecuritydemo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Author ChangLu
 * @Date 3/22/2022 3:10 PM
 * @Description 测试控制器
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}