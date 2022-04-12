package com.changlu.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zf_think")
@ApiModel(value="ZfThinkModel对象", description="")
public class ZfThinkModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "个人心得主键id")
      @TableId(value = "think_id", type = IdType.AUTO)
    private Long thinkId;

    @ApiModelProperty(value = "感悟思考")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "用户主键id")
    @TableField("user_id")
    private Long userId;


}
