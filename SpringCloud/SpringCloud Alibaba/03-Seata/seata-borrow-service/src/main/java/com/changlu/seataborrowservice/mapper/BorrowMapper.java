package com.changlu.seataborrowservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.seataborrowservice.pojo.BorrowModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
public interface BorrowMapper extends BaseMapper<BorrowModel> {

    @Select("select * from db_borrow where user_id = #{userId} AND book_id = #{bookId}")
    BorrowModel getBorrow(@Param("userId")Integer userId, @Param("bookId")Integer bookId);

    @Insert("insert into db_borrow(user_id, book_id) values(#{userId}, #{bookId})")
    int addBorrow(@Param("userId")Integer userId, @Param("bookId")Integer bookId);

}
