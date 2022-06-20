package com.changlu.springbootdemoredis.service;

import com.changlu.springbootdemoredis.pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:17 PM
 */
@Service
public class UserServiceImpl implements UserService{

    @Override
    @Cacheable(cacheNames = "cache_user", key="'user_' + #id")
    public User getUserById(Integer id) {
        return new User("changlu", "123456", id);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
