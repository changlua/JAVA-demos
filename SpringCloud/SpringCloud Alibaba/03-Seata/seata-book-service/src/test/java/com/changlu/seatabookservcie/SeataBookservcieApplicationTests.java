package com.changlu.seatabookservcie;

import com.changlu.seatabookservcie.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SeataBookservcieApplicationTests {

    @Resource
    private BookMapper bookMapper;

    @Test
    void contextLoads() {
        System.out.println(bookMapper.bookRemain(1));
        System.out.println(bookMapper.minusBookRemain(1));
    }

}
