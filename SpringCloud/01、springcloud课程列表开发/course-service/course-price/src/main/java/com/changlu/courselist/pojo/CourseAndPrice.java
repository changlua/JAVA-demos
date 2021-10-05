package com.changlu.courselist.pojo;

import com.changlu.democourse.pojo.Course;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName CourseAndPrice
 * @Author ChangLu
 * @Date 2021/10/5 7:15
 * @Description TODO
 */
@Data
public class CourseAndPrice extends Course implements Serializable {

    private static final long serialVersionUID = -6855794470748667710L;

    private BigDecimal price;
}