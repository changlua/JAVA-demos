package com.changlu.springsecurityexer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ErrorController
 * @Author ChangLu
 * @Date 2021/9/19 16:57
 * @Description TODO
 */

@RestController
public class ErrorController {

    @RequestMapping("/error/403")
    public String error403(){
        return "DIY error403";
    }

}