package com.changlu.ribbonprovidera.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:58 PM
 */
@RestController
public class ProviderController {

    @GetMapping("/hello")
    public String hello(){
        return "我是服务提供者aaa";
    }

}
