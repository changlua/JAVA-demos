package com.changlu.sentineluserservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: changlu
 * @Date: 12:31 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;
    private String name;
    private String sex;
    private String hobby;
    private String likeBook;

}
