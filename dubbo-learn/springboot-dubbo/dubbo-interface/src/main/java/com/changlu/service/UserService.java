package com.changlu.service;

import com.changlu.pojo.User;

/**
 * @ClassName UserService
 * @Author ChangLu
 * @Date 6/7/2022 2:44 PM
 * @Description 用户业务接口
 */
public interface UserService {
    
    User getUserById(Integer id);
    
}
