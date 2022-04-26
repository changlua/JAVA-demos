package com.changlu.springsecuritydemo05jwt.controller;

import com.changlu.springsecuritydemo05jwt.domain.ResponseResult;
import com.changlu.springsecuritydemo05jwt.domain.pojo.User;
import com.changlu.springsecuritydemo05jwt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @Author ChangLu
 * @Date 3/25/2022 2:44 PM
 * @Description 登录控制器
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User loginUser){
        return loginService.login(loginUser);
    }

//    方案一：自定义注销接口
//    @GetMapping("/logout")
//    public ResponseResult logout(){
//        return loginService.logout();
//    }
}