package com.changlu.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("zf_major")
@ApiModel(value="ZfMajorModel对象", description="")
public class ZfMajorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业主键id")
      @TableId(value = "major_id", type = IdType.AUTO)
    private Long majorId;

    @ApiModelProperty(value = "专业名称")
    @TableField("major_name")
    private String majorName;


}
