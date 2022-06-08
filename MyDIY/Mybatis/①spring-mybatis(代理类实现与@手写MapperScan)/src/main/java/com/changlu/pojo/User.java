package com.changlu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName User
 * @Author ChangLu
 * @Date 2021/8/22 22:47
 * @Description TODO
 */
@Data
public class User {
    private Long id;
    private String lastName;
    private Integer age;
    private String email;
    private Date gmtCreate;
    private Date gmtModified;
}