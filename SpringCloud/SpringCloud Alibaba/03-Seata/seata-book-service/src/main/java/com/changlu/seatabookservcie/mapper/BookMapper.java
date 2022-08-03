package com.changlu.seatabookservcie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.seatabookservcie.pojo.BookModel;
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
public interface BookMapper extends BaseMapper<BookModel> {

    @Select("SELECT count from db_book WHERE id = #{id}")
    int bookRemain(Integer id);

    @Update("UPDATE db_book set count = count - 1 where id = #{id} and count > 0")
    int minusBookRemain(Integer id);

}
