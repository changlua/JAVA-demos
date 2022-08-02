package com.changlu.sentineluserservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.changlu.sentineluserservice.feign.UserBookFeign;
import com.changlu.sentineluserservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description:
 * @Author: changlu
 * @Date: 12:31 PM
 */
@RestController
public class UserController {

    @Autowired
    private UserBookFeign userBookFeign;

    @GetMapping("/user")
    public User getUser() {
        User user = new User(1L, "changlu", "男", "打篮球", null);
        //远程调用
        String userLikeBook = userBookFeign.getUserLikeBook(user.getId());
        user.setLikeBook(userLikeBook);
        return user;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/testparams")
    @SentinelResource(value = "testparams",
                      blockHandler = "handlerTestHotKey",
                      fallback = "except",  //fallback指定出现异常时的替代方案
                      exceptionsToIgnore = IOException.class //忽略那些异常，也就是说这些异常出现时不使用替代方案
                    )
    public String testParams(@RequestParam(value = "a", required = false)String a,
                             @RequestParam(value = "b", required = false)String b,
                             @RequestParam(value = "c", required = false)String c) throws RuntimeException {
        throw new RuntimeException("自定义抛出异常！");
//        return "a:" + a + ", b:" + b + ", c:" + c;
    }

    //替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
    public String except(String a, String b, String c, Throwable t){
        return t.getMessage();
    }

    //必须要设置为public，否则会抛出异常
    public String handlerTestHotKey(String a, String b, String c, BlockException exception) {
        return "对不起, 当前流量过高...";
    }





}
