package com.changlu.controller;

import com.changlu.annontions.ApiIdempotent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单控制器
 * @Author: changlu
 * @Date: 10:03 AM
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    //幂等性注解
    @ApiIdempotent
    @PostMapping
    public Object createOrder() {
        return "创建订单成功！";
    }

}
