package com.changlu.seatauserservice;

import com.changlu.seatauserservice.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SeataUserserviceApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.getUserRemainBook(1));
        System.out.println(userMapper.minusUserBookCount(1));
    }

}
