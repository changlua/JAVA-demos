package com.changlu.customerservice.feign.hystrix;

import com.changlu.customerservice.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者-借车熔断器
 * @Author: changlu
 * @Date: 9:19 AM
 */
@Component
public class CustomerRentHystrix implements CustomerRentFeign {
    @Override
    public String rent() {
        return "租车成功！（熔断器）";
    }
}
