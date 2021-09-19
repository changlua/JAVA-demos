package com.changlu.springsecurityexer.mapper;

import java.util.List;

import com.changlu.springsecurityexer.entity.PermissionEntity;
import org.apache.ibatis.annotations.Select;


public interface PermissionMapper {

    @Select(" select * from sys_permission ")
    List<PermissionEntity> findAllPermission();

}
