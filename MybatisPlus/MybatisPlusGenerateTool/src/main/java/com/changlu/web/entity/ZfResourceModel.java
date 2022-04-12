package com.changlu.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("zf_resource")
@ApiModel(value="ZfResourceModel对象", description="")
public class ZfResourceModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源主键id")
      @TableId("res_id")
    private Long resId;

    @ApiModelProperty(value = "资源url地址")
    @TableField("res_url")
    private String resUrl;

    @ApiModelProperty(value = "资源标识(1表示竞赛团队)")
    @TableField("res_flag")
    private Boolean resFlag;

    @ApiModelProperty(value = "图片上传名称")
    @TableField("res_name")
    private String resName;

    @ApiModelProperty(value = "上传时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "关联表id(其他表主键id)")
    @TableField("table_id")
    private Long tableId;


}
