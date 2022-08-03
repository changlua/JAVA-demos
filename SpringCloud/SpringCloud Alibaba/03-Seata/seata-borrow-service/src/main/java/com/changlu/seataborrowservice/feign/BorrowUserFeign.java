package com.changlu.seataborrowservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: changlu
 * @Date: 7:52 PM
 */
@FeignClient(value = "user-service")
public interface BorrowUserFeign {

    @GetMapping("/user/remainbook/{uid}")
    int getUserRemainBook(@PathVariable("uid")Integer uid);

    @GetMapping("/user/minusbook/{uid}")
    int minusUserBookCount(@PathVariable("uid")Integer uid);

}
