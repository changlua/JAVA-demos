package com.changlu.springsecuritydemo05jwt.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changlu.springsecuritydemo05jwt.domain.LoginUser;
import com.changlu.springsecuritydemo05jwt.domain.pojo.User;
import com.changlu.springsecuritydemo05jwt.mapper.MenuMapper;
import com.changlu.springsecuritydemo05jwt.mapper.UserMapper;
import com.changlu.springsecuritydemo05jwt.security.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserDetailServiceImpl
 * @Author ChangLu
 * @Date 3/25/2022 2:01 PM
 * @Description 自定义认证数据源
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询用户
        User user = userMapper.loadUserByUsername(username);
        //2、若是没有用户就抛出异常
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("用户名有误！");
        }
        //3、若是查询到用户就去获取用户的权限，最后返回
        List<String> roles = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,roles);
//        return null;
    }
}
