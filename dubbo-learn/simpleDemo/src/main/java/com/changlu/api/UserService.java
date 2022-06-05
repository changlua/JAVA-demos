package com.changlu.api;

import com.changlu.pojo.User;

/**
 * @ClassName UserService
 * @Author ChangLu
 * @Date 6/5/2022 8:42 PM
 * @Description 用户业务接口
 */
public interface UserService {

    User getUserById(Integer id);

}
