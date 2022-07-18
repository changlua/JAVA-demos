package com.changlu.feign;

import com.changlu.domain.Order;
import com.changlu.feign.hystrix.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:39 PM
 */
@FeignClient(value = "order-service", fallback = UserOrderFeignHystrix.class)
public interface UserOrderFeign {

    @GetMapping("/order/{id}")
    Order getOrderById(@PathVariable("id")Integer id);

}
