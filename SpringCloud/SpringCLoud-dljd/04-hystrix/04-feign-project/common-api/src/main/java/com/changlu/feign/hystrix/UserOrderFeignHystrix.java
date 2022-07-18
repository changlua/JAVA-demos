package com.changlu.feign.hystrix;

import com.changlu.domain.Order;
import com.changlu.feign.UserOrderFeign;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:43 PM
 */
@Component
public class UserOrderFeignHystrix implements UserOrderFeign {
    @Override
    public Order getOrderById(Integer id) {
        return null;
    }
}
