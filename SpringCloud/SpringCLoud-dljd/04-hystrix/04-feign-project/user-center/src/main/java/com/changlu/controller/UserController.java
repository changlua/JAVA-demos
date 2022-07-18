package com.changlu.controller;

import com.changlu.domain.Order;
import com.changlu.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:53 PM
 */
@RestController
public class UserController {

    @Autowired
    private UserOrderFeign feign;

    @GetMapping("/user/order/{userId}")
    public Order getOrderByUserId(@PathVariable("userId")Integer userId) {
        //根据用户id获取到订单id
        Integer id = 1111;
        Order order = feign.getOrderById(id);
        return order;
    }

}
