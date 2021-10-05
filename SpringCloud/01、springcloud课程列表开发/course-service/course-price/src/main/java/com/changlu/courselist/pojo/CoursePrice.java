package com.changlu.courselist.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Course
 * @Author ChangLu
 * @Date 2021/10/4 12:42
 * @Description TODO
 */
@Data
public class CoursePrice implements Serializable {

    private static final long serialVersionUID = -6849794470748667710L;

    private Integer id;
    private Integer courseId;
    private BigDecimal price;

}