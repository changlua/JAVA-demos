package com.changlu.controller;

import com.changlu.pojo.User;
import com.changlu.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author ChangLu
 * @Date 6/7/2022 3:04 PM
 * @Description 用户控制器
 */
@RestController
public class UserController {

    //使用dubbo提供依赖注入注解，指定版本号
    @Reference(version = "${demo.service.version}")
    private UserService userService;

    @GetMapping("/user")
    public User userInfo(){
        return userService.getUserById(123);
    }

}
