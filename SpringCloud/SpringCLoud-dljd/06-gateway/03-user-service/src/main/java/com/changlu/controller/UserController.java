package com.changlu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:16 AM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public Map<String, Object> getUser() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "成功获取到用户信息");
        return result;
    }

}
