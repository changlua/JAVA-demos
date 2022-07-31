package com.changlu.nacosclienta.controller;

import com.changlu.nacosclienta.feign.MemberFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:19 PM
 */
@RestController
public class TestController {

    @Autowired
    private MemberFeign memberFeign;

    @GetMapping("/testClientA")
    public String testClientA() {
        String info = memberFeign.info();
        return info;
    }

}
