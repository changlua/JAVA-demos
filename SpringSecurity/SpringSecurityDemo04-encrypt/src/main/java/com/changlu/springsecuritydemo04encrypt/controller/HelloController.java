package com.changlu.springsecuritydemo04encrypt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Author ChangLu
 * @Date 3/24/2022 5:38 PM
 * @Description 测试
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}