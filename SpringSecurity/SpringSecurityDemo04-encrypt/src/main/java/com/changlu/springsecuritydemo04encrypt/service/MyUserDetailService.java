package com.changlu.springsecuritydemo04encrypt.service;

import com.changlu.springsecuritydemo04encrypt.dao.UserDao;
import com.changlu.springsecuritydemo04encrypt.entity.Role;
import com.changlu.springsecuritydemo04encrypt.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MyUserDetailService
 * @Author ChangLu
 * @Date 3/24/2022 8:05 PM
 * @Description 自定义认证服务源、自动升级密码
 */
@Service
public class MyUserDetailService implements UserDetailsService, UserDetailsPasswordService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.loadUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Role> roles = userDao.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        //1、更新密码
        final Integer status = userDao.updatePassword(user.getUsername(), newPassword);
        //2、若是更新成功，那么将内存中的用户进行更新密码
        if (status == 1){
            ((User)user).setPassword(newPassword);
        }
        return user;
    }


}