package com.changlu.springsecuritydemo3web.service;

import com.changlu.springsecuritydemo3web.dao.UserDao;
import com.changlu.springsecuritydemo3web.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @ClassName MyUserDeatailService
 * @Author ChangLu
 * @Date 3/23/2022 3:51 PM
 * @Description 自定义认证用户
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserDao userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、根据用户名创建用户对象
        User user = userMapper.loadUserByUsername(username);
        if(ObjectUtils.isEmpty(user))
            throw new UsernameNotFoundException("用户名不存在");
        //2、获取角色
        user.setRoles(userMapper.getRolesByUid(user.getId()));
        return user;
    }
}