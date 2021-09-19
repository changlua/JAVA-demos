package com.changlu.springsecurityexer.entity;

import lombok.Data;

// 角色信息表
@Data
public class RoleEntity {
	private Integer id;
	private String roleName;
	private String roleDesc;
}
