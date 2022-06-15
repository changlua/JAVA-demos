package com.changlu.serialize.pojo;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author ChangLu
 * @Date 6/14/2022 8:45 PM
 * @Description 用户类
 */
public class User implements Serializable {

    private String name;
    private Integer age;

    public User(){
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
