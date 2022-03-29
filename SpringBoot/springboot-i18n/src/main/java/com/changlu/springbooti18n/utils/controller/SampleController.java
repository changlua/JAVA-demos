package com.changlu.springbooti18n.utils.controller;

import com.changlu.springbooti18n.utils.MessageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SimpleController
 * @Author ChangLu
 * @Date 3/29/2022 9:29 AM
 * @Description 测试控制器
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        return "hello kaile!";
    }

    @GetMapping("/testI18n")
    public String test() {
        String message = MessageUtils.message("not null");
        return message;
    }
}
