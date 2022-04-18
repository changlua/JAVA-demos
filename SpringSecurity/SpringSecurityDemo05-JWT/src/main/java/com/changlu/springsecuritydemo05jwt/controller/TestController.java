package com.changlu.springsecuritydemo05jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author ChangLu
 * @Date 3/25/2022 10:59 AM
 * @Description 测试控制器
 */
@RestController
public class TestController {

    //@PreAuthorize("hasAnyAuthority('sys:user:update')")
    @PreAuthorize("hasRole('sys:user:delete')")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //自定义权限
    @PreAuthorize("@ex.hasAuthority('sys:user:delete')")
    @GetMapping("/test")
    public String test(){
        return "test";
    }

}