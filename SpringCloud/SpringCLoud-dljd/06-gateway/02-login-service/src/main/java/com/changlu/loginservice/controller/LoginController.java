package com.changlu.loginservice.controller;

import com.changlu.loginservice.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description:
 * @Author: changlu
 * @Date: 8:20 PM
 */
@RestController
public class LoginController {

    private static final String token = "700d7a8d-262a-447a-8254-9dd9ead6a0e2";

    @GetMapping("/doLogin")
    public String doLogin() {
        return UUID.randomUUID().toString();
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestBody User user) {
        System.out.println("dologin进行登录：" + user);
        //数据库进行认证，这里的话直接返回一个token
        return token;
    }

    public static void main(String[] args) {
//        ZonedDateTime dateTime=ZonedDateTime.now();
//        System.out.println(dateTime);
        String s = UUID.randomUUID().toString();
        System.out.println(s);
    }

}
