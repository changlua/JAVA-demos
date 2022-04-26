package com.changlu.springsecuritydemo05jwt.mp;

import com.changlu.springsecuritydemo05jwt.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName MpTest
 * @Author ChangLu
 * @Date 3/25/2022 1:51 PM
 * @Description MybatisPlus测试
 */
@SpringBootTest
public class MpTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void context(){
        System.out.println(userMapper.loadUserByUsername("changlu"));
    }

}