package com.changlu.loginservice.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @Author: changlu
 * @Date: 9:20 AM
 */
@Data
public class User implements Serializable {
    private String username;
    private String password;
}
