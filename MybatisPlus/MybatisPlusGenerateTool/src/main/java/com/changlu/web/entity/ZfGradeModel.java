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
@TableName("zf_grade")
@ApiModel(value="ZfGradeModel对象", description="")
public class ZfGradeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年级主键id")
      @TableId(value = "grade_id", type = IdType.AUTO)
    private Long gradeId;

    @ApiModelProperty(value = "年级")
    @TableField("grade_num")
    private Integer gradeNum;


}
