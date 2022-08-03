package com.changlu.seataborrowservice;

import com.changlu.seataborrowservice.mapper.BorrowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SeataBorrowServiceApplicationTests {

    @Resource
    private BorrowMapper borrowMapper;

    @Test
    void contextLoads() {
        System.out.println(borrowMapper.getBorrow(1, 2));
        System.out.println(borrowMapper.addBorrow(1, 3));
    }

}
