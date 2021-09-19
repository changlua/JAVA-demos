package com.changlu.springsecurityexer.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

// 用户信息表
@Data
public class UserEntity implements UserDetails {  //1、必须要继承UserDetails接口

	private Integer id;
	private String username;
	private String realname;
	private String password;
	private Date createDate;
	private Date lastLoginTime;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	// 2、用户所有权限，实现getAuthorities()方法
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
