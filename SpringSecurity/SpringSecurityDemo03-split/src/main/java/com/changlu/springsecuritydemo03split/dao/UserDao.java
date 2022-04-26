package com.changlu.springsecuritydemo03split.dao;

import com.changlu.springsecuritydemo03split.entity.Role;
import com.changlu.springsecuritydemo03split.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName UserDao
 * @Author ChangLu
 * @Date 3/23/2022 4:03 PM
 * @Description 用户Mapper
 */
@Mapper
public interface UserDao {
    //根据用户名查询用户
    User loadUserByUsername(String username);

    //根据用户id查询角色
    List<Role> getRolesByUid(Integer uid);
}