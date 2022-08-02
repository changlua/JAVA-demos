package com.changlu.sentineluserservice.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:57 AM
 */
@RestController
public class CommonController {

    @RequestMapping("/blocked")
    public JSONObject blocked() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 403);
        jsonObject.put("success", false);
        jsonObject.put("message", "您的请求频率过快，请稍后再试！");
        return jsonObject;
    }

}
