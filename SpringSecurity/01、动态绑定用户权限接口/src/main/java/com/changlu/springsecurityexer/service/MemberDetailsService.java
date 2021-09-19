package com.changlu.springsecurityexer.service;

import com.changlu.springsecurityexer.entity.PermissionEntity;
import com.changlu.springsecurityexer.entity.UserEntity;
import com.changlu.springsecurityexer.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MemberDetailsService
 * @Author ChangLu
 * @Date 2021/9/19 18:50
 * @Description TODO
 */
@Service
@Slf4j
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询到用户
        UserEntity user = userMapper.findByUsername(username);
        if(user == null){
            return null;
        }
        //2、查询该用户对应的权限
        List<PermissionEntity> permissionList = userMapper.findPermissionByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        permissionList.forEach((permission)->{
            authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
        });
        log.info(">>permissionList:{}<<",permissionList);
        //3、设置用户权限
        user.setAuthorities(authorities);
        return user;
    }
}