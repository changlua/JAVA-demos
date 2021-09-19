package com.changlu.springsecurityexer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author ChangLu
 * @Date 2021/9/19 15:38
 * @Description TODO
 */
@RestController
public class UserController {

    @GetMapping("/showMember")
    public String showMember(){
        return "showMember";
    }

    @GetMapping("/addMember")
    public String addMember(){
        return "addMember";
    }

    @GetMapping("/delMember")
    public String delete(){
        return "delMember";
    }

    @GetMapping("/updateMember")
    public String updateMember(){
        return "updateMember";
    }

}