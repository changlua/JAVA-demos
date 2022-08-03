package com.changlu.seatauserservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.seatauserservice.pojo.UserModel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
public interface UserMapper extends BaseMapper<UserModel> {

    @Select("SELECT book_count from db_user WHERE uid = #{uid}")
    int getUserRemainBook(Integer uid);

    @Update("UPDATE db_user set book_count = book_count - 1 where uid = #{uid}  and book_count > 0")
    int minusUserBookCount(Integer uid);

}
