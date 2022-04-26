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
@TableName("zf_race")
@ApiModel(value="ZfRaceModel对象", description="")
public class ZfRaceModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "竞赛主键id")
      @TableId("race_id")
    private Long raceId;

    @ApiModelProperty(value = "竞赛名称")
    @TableField("race_name")
    private String raceName;

    @ApiModelProperty(value = "竞赛成员（存储参与成员的id，使用,分割）")
    @TableField("race_members")
    private String raceMembers;

    @ApiModelProperty(value = "竞赛开始时间")
    @TableField("race_begin_time")
    private Date raceBeginTime;

    @ApiModelProperty(value = "竞赛结束时间")
    @TableField("race_end_time")
    private Date raceEndTime;

    @ApiModelProperty(value = "标识个人还是团队(1是个人,2是团队)")
    @TableField("race_flag")
    private Boolean raceFlag;

    @ApiModelProperty(value = "经验总结")
    @TableField("race_summarize")
    private String raceSummarize;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "用户id(个人就是填写1个id，团队多个id使用,分割开)")
    @TableField("user_ids")
    private String userIds;


}
