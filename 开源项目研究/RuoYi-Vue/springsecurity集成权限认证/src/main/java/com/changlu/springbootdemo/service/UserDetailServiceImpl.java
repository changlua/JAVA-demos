package com.changlu.springbootdemo.service;

import com.alibaba.fastjson.JSON;
import com.changlu.springbootdemo.common.domain.LoginUser;
import com.changlu.springbootdemo.domain.pojo.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @ClassName UserDetailServiceImpl
 * @Author ChangLu
 * @Date 2021/9/25 17:43
 * @Description TODO
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    //该方法传入用户名,一般是来进行用户名查询、用户权限查询操作
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DAO：根据用户姓名查询

        //1、测试用户是否存在
        //2、若是存在，检测是账号否被删除(根据某个字段)
        //3、若是存在，检测账号是否被停用(根据某个字段)
        SysUser sysUser = new SysUser();
        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(SysUser sysUser){
        return new LoginUser(sysUser,new ArrayList<>());
    }

}