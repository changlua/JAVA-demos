package com.changlu.springbootdemo.common.domain;

import com.changlu.springbootdemo.domain.pojo.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName LoginUser
 * @Author ChangLu
 * @Date 2021/9/25 17:48
 * @Description TODO
 */

public class LoginUser implements UserDetails {


    //pojo实体类
    private SysUser sysUser;

    //权限集合，通过DB查询出来将权限
    List<GrantedAuthority> authorities;

    //构造器
    public LoginUser(SysUser sysUser, List<GrantedAuthority> authorities) {
        this.sysUser = sysUser;
        this.authorities = authorities;
    }

    //默认需要重写的七个方法
    //获取权限，之后通过某个用户判断是否有权限就是从这里拿的
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}