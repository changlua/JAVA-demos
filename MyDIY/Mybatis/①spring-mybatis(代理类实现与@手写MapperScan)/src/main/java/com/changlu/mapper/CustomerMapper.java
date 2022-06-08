package com.changlu.mapper;

import com.changlu.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName CustomerMapper
 * @Author ChangLu
 * @Date 2021/8/23 0:35
 * @Description TODO
 */
public interface CustomerMapper {

    @Select("select * from user")
    List<User> queryUserList();
}
