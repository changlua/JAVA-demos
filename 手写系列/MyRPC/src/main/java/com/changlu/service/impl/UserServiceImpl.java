package com.changlu.service.impl;


import com.changlu.pojo.User;
import com.changlu.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Author ChangLu
 * @Date 5/31/2022 10:57 AM
 * @Description 用户业务实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"的用户");
        // 模拟从数据库中取用户的行为
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }
}
