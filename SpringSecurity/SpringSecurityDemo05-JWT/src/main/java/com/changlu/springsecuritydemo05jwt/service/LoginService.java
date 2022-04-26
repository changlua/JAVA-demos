package com.changlu.springsecuritydemo05jwt.service;

import com.changlu.springsecuritydemo05jwt.domain.ResponseResult;
import com.changlu.springsecuritydemo05jwt.domain.pojo.User;

/**
 * @ClassName LoginService
 * @Author ChangLu
 * @Date 3/25/2022 2:48 PM
 * @Description 业务层登录接口
 */
public interface LoginService {

    ResponseResult login(User user);

//    ResponseResult logout();
}