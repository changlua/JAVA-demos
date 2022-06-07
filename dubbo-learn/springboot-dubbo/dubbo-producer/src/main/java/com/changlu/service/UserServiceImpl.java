package com.changlu.service;

import com.changlu.pojo.User;
import org.apache.dubbo.config.annotation.Service;

/**
 * @ClassName UserServiceImpl
 * @Author ChangLu
 * @Date 6/7/2022 2:51 PM
 * @Description 用户业务实现类
 */
//注意：这里的service是dubbo中的service注解
@Service(version = "${demo.service.version}")
public class UserServiceImpl implements UserService{
    @Override
    public User getUserById(Integer id) {
        return new User("changlu", "男", 18);
    }
}
