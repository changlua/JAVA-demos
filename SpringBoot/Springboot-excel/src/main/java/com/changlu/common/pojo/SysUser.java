package com.changlu.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.changlu.PoiExcel.poi.MyExcelHandler;
import com.changlu.PoiExcel.poi.annoation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
@TableName(value="sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @TableId(value = "user_id",type = IdType.AUTO)
    @Excel(name = "用户序号", prompt = "用户编号",isStatistics = true)  //prompt：就是用于在代码层面提示的信息(excel没有)  //isStatistics：该列最后会统计出该属性的合计
    private Long userId;

    /** 用户账号 */
    @Excel(name = "账号名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 用户类型 */
    @Excel(name = "用户类型")
    private String userType;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别",readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    @Excel(name = "用户头像",height = 50, cellType = Excel.ColumnType.IMAGE)  //cellType：导出的格式(0数字 1字符串 2图片)
    private String avatar;

    /** 密码 */
    @JsonIgnore
//    @Excel(name = "密码",handler = MyExcelHandler.class, args={"1","2"})  //handler：自定义处理器，args：传入到自定义处理器中的args参数用于来进行自定义处理
    @Excel(name = "密码")
    private String password;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态",readConverterExp = "0=正常,1=停用")  //readConverterExp：内容转换表达式
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @Excel(name = "删除标志")
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")  //width：单元格宽度，dateFormat：日期格式化
    private Date loginDate;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 个人描述 */
    @Excel(name = "个人描述")
    private String description;

    /** 个人照片 */
    @Excel(name = "个人照片")
    private String perImg;

    /** 专业id */
    @Excel(name = "专业id")
    private String majorId;

    /** 年纪id */
    @Excel(name = "年纪id")
    private String gradeId;

    public SysUser(String username, String nickName, String password){
        this.userName = username;
        this.nickName = nickName;
        this.password = password;
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }
}
