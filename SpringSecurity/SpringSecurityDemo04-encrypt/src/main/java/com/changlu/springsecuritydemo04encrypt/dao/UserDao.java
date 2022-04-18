package com.changlu.springsecuritydemo04encrypt.dao;

import com.changlu.springsecuritydemo04encrypt.entity.Role;
import com.changlu.springsecuritydemo04encrypt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    //更新用户密码
    Integer updatePassword(@Param("username") String username, @Param("password") String password);

}