package com.changlu.rentcarservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:18 PM
 */
@RestController
public class RentCarController {

    @GetMapping("/rent")
    public String rent() {
        return "租车成功！";
    }

}
