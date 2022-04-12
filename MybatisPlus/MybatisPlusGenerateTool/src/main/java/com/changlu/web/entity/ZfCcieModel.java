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
@TableName("zf_ccie")
@ApiModel(value="ZfCcieModel对象", description="")
public class ZfCcieModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "获奖证书主键id")
      @TableId("ccie_id")
    private Long ccieId;

    @ApiModelProperty(value = "证书名称")
    @TableField("ccie_name")
    private String ccieName;

    @ApiModelProperty(value = "获奖证书图片")
    @TableField("ccie_img")
    private String ccieImg;

    @ApiModelProperty(value = "获奖时间")
    @TableField("ccci_get_time")
    private Date ccciGetTime;

    @ApiModelProperty(value = "经验总结")
    @TableField("ccie_think")
    private String ccieThink;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;


}
