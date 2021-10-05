package com.changlu.courselist.service;

import com.changlu.courselist.pojo.CourseAndPrice;
import com.changlu.courselist.pojo.CoursePrice;

import java.util.List;

/**
 * @ClassName CoursePriceService
 * @Author ChangLu
 * @Date 2021/10/4 13:16
 * @Description TODO
 */
public interface CoursePriceService {

    /**
     * 根据课程id查询到课程价格
     * @param courseId
     * @return
     */
    CoursePrice getCoursePriceByCourseId(Integer courseId);

    /**
     * 查询出所有的课程及价格
     * @return
     */
    List<CourseAndPrice> getAllCourseAndPrice();
}
