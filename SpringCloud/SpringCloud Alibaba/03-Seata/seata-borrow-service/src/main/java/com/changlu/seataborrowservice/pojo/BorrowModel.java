package com.changlu.seataborrowservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("db_borrow")
public class BorrowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Integer userId;

    @TableField("book_id")
    private Integer bookId;


}
