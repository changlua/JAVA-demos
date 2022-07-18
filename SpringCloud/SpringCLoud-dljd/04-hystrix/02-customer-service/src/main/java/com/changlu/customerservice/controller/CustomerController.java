package com.changlu.customerservice.controller;

import com.changlu.customerservice.feign.CustomerRentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:28 PM
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerRentFeign customerRentFeign;

    @GetMapping("/customerRent")
    public String customerRent() {
        System.out.println("来进行访问租车了！");
        String rent = customerRentFeign.rent();
        return rent;
    }

}
