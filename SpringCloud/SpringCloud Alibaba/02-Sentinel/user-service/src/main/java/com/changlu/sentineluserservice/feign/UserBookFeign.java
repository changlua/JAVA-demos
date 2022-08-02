package com.changlu.sentineluserservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: changlu
 * @Date: 12:34 PM
 */
@FeignClient(value = "book-service", fallback = UserClientFallback.class)
public interface UserBookFeign {

    @GetMapping("/book/like/{id}")
    String getUserLikeBook(@PathVariable("id")Long id);

}
