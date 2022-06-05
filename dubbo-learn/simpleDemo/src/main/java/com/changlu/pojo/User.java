package com.changlu.pojo;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author ChangLu
 * @Date 6/5/2022 8:40 PM
 * @Description 用户类
 */
public class User implements Serializable {

    private String name;
    private String sex;
    private Integer age;

    public User(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
