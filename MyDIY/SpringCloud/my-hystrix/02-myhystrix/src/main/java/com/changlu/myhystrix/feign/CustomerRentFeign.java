package com.changlu.myhystrix.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:30 PM
 */
@FeignClient(value = "rent-car-service")
public interface CustomerRentFeign {

    @GetMapping("/rent")
    public String rent();

}
