package com.changlu.resttemplate.controller;

import com.changlu.resttemplate.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试控制器
 * @Author: changlu
 * @Date: 4:04 PM
 */
@RestController
public class TestController {

    @GetMapping("/testGet")
    public String get(String name) {
        System.out.println(name);
        return "ok";
    }

    /**
     * post传参 两种
     *  json参数的核心  header content-type = application/json;charset=utf-8
     *
     * @return
     */
    @PostMapping("/testPost1")
    public String testPost1(@RequestBody User user) {
        System.out.println(user);
        return "ok";
    }

    /**
     * 接收表单参数
     * <form action=/testPost2
     * <input name=ccc value=ssss
     *  header content-type = application/x-www-form-urlencoded
     * @param user
     * @return
     */
    @PostMapping("/testPost2")
    public String testPost2(User user) {
        System.out.println(user);
        return "ok";
    }

}
