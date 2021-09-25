package com.changlu.service;

import com.changlu.mapper.CustomerMapper;
import com.changlu.mapper.OrderMapper;
import com.changlu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author ChangLu
 * @Date 2021/8/22 22:48
 * @Description TODO
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerMapper customerMapper;

    public void test(){
        System.out.println(userMapper.queryUserList());
        System.out.println(orderMapper.queryUserList());
        System.out.println(customerMapper.queryUserList());
    }
}