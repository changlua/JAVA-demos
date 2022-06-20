package com.changlu.springbootdemoredis.controller;

import com.changlu.springbootdemoredis.pojo.User;
import com.changlu.springbootdemoredis.service.UserService;
import com.changlu.springbootdemoredis.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:03 PM
 */
@RestController
public class HelloController {

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/hello")
    public String hello(){
        redisCache.setCacheObject("changlu", 666);
        return "success";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

}
