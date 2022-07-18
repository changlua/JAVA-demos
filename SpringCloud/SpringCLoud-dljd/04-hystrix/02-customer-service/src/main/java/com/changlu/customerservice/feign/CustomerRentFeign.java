package com.changlu.customerservice.feign;

import com.changlu.customerservice.feign.hystrix.CustomerRentHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:30 PM
 */
@FeignClient(value = "rent-car-service", fallback = CustomerRentHystrix.class)
public interface CustomerRentFeign {

    @GetMapping("/rent")
    public String rent();

}
