package com.changlu.springsecuritydemo03split.service;

import com.changlu.springsecuritydemo03split.dao.UserDao;
import com.changlu.springsecuritydemo03split.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @ClassName MyUserDetailService
 * @Author ChangLu
 * @Date 3/23/2022 6:05 PM
 * @Description 自定义认证数据源
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserDao userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //方式一：直接返回写死数据
//        if (username != null && username.equals("changlu")){
//            return User.withUsername("changlu").password("{noop}123").roles("admin","super").build();
//        }else{
//            throw new UsernameNotFoundException("无该用户名账户");
//        }
        //方式二：通过dao数据库查询
        User user = userMapper.loadUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("没有该账户");
        }
        user.setRoles(userMapper.getRolesByUid(user.getId()));
        return user;
    }
}