package com.changlu.mapper;

import com.changlu.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName OrderMapper
 * @Author ChangLu
 * @Date 2021/8/22 23:07
 * @Description TODO
 */
public interface OrderMapper {

    @Select("select * from user")
    List<User> queryUserList();
}