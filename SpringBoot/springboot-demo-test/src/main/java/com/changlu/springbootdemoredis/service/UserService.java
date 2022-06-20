package com.changlu.springbootdemoredis.service;

import com.changlu.springbootdemoredis.pojo.User;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:17 PM
 */
public interface UserService {

    User getUserById(Integer id);

    User updateUser(User user);

}
