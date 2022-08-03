package com.changlu.seataborrowservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: changlu
 * @Date: 7:52 PM
 */
@FeignClient(value = "book-service")
public interface BorrowBookFeign {

    @GetMapping("/book/minus/{id}")
    int minusBookRemain(@PathVariable("id") Integer id);

    @GetMapping("/book/remain/{id}")
    int bookRemain(@PathVariable("id") Integer id);

}
