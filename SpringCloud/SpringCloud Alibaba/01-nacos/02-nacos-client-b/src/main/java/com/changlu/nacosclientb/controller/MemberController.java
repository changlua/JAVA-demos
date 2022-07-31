package com.changlu.nacosclientb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:15 PM
 */
@RestController
public class MemberController {

    @GetMapping("/info")
    public String info() {
        String info = "client-b：" + UUID.randomUUID().toString();
        return info;
    }

}
