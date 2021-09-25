package com.changlu.mapper;

import com.changlu.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author ChangLu
 * @Date 2021/8/22 22:48
 * @Description TODO
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> queryUserList();
}
