package com.changlu.resttemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户类
 * @Author: changlu
 * @Date: 4:04 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;

    private Integer age;

    private Double price;
}
