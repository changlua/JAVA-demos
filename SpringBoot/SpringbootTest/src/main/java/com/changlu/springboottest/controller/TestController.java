package com.changlu.springboottest.controller;

import com.changlu.springboottest.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Author ChangLu
 * @Date 3/30/2022 3:34 PM
 * @Description TODO
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> res = new HashMap();
        res.put("code", 200);
        res.put("data", new User("changlu", 19));
        return res;
    }

}
