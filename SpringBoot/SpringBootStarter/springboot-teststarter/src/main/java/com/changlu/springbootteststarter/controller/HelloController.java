package com.changlu.springbootteststarter.controller;

import com.changlu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 5:35 PM
 */
@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String testUserService() {
        return userService.getUserName("changlu");
    }

}
