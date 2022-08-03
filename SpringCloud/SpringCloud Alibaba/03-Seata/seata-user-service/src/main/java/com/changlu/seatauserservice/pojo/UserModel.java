package com.changlu.seatauserservice.pojo;

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
@TableName("db_user")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("uid")
    private Integer uid;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("book_count")
    private Integer bookCount;


}
