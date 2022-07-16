package com.changlu.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: changlu
 * @Date: 6:45 PM
 */
@RestController
public class OrderController {

    @GetMapping("/doOrder")
    public String doOrder() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "牛奶泡芙";
    }

}
