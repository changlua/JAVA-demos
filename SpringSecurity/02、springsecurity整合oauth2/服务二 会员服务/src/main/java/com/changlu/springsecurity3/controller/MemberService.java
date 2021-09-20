package com.changlu.springsecurity3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberService
 * @Author ChangLu
 * @Date 2021/9/20 11:09
 * @Description 对于会员服务开发的接口
 */
@RestController
public class MemberService {
    @GetMapping("/getMember")
    public String getMember() {
        return "我是会员服务接口";
    }
}