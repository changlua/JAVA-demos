package com.changlu.controller;

import com.changlu.domain.Order;
import com.changlu.feign.UserOrderFeign;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:38 PM
 */
@RestController
public class OrderController implements UserOrderFeign {

        @Override
        public Order getOrderById(Integer id) {
            Order order = Order.builder()
                    .orderId(id)
                    .price(100.0)
                    .orderName("牛奶布丁").build();
            return order;
        }


}
