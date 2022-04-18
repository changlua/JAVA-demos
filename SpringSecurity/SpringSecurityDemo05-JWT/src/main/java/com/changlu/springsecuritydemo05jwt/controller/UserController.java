package com.changlu.springsecuritydemo05jwt.controller;

import com.changlu.springsecuritydemo05jwt.domain.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author ChangLu
 * @Date 3/25/2022 6:10 PM
 * @Description 用户控制器
 */
@RestController
public class UserController {

    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> result = new HashMap<>(1);
        result.put("authentication", authentication);
        return new ResponseResult(200, result);
    }

}