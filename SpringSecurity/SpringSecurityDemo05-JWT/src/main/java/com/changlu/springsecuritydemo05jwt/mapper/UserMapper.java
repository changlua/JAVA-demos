package com.changlu.springsecuritydemo05jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.springsecuritydemo05jwt.domain.pojo.User;

public interface UserMapper extends BaseMapper<User> {

    //根据用户名查询用户
    User loadUserByUsername(String username);

}