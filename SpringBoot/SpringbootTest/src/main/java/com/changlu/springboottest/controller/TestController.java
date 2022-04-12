package com.changlu.springboottest.controller;

import com.changlu.springboottest.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author ChangLu
 * @Date 3/30/2022 3:34 PM
 * @Description TODO
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public Object test(User user){
        System.out.println("user:" + user);
        return user;
    }

}
