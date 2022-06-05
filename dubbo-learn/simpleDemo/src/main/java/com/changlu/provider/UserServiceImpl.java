package com.changlu.provider;

import com.changlu.api.UserService;
import com.changlu.pojo.User;

/**
 * @ClassName UserServiceImpl
 * @Author ChangLu
 * @Date 6/5/2022 8:46 PM
 * @Description 用户实现类
 */
public class UserServiceImpl implements UserService {
    
    @Override
    public User getUserById(Integer id) {
        return new User("changlu", "男", 18);
    }
    
}
