package com.changlu.service;


import com.changlu.pojo.User;

/**
 * @ClassName UserServcice
 * @Author ChangLu
 * @Date 5/31/2022 10:56 AM
 * @Description 用户业务接口
 */
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id)throws Exception;
    // 给这个服务增加一个功能
    Integer insertUserId(User user)throws Exception;
}
