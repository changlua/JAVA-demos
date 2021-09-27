package com.changlu.springbootdemo.domain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysUser
 * @Author ChangLu
 * @Date 2021/9/25 17:52
 * @Description TODO
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 部门ID */
    private Long deptId;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    @JsonIgnore
    private String password;

    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private Date loginDate;

    @JsonIgnore
    public String getUserName() {
        return userName;
    }


    public boolean iAccountNonLocked() {
        return true;
    }
}