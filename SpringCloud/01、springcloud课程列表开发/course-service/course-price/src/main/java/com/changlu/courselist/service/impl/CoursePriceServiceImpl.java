package com.changlu.courselist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changlu.courselist.client.CourseListClient;
import com.changlu.courselist.mapper.CoursePriceMapper;
import com.changlu.courselist.pojo.CourseAndPrice;
import com.changlu.courselist.pojo.CoursePrice;
import com.changlu.courselist.service.CoursePriceService;
import com.changlu.democourse.pojo.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CoursePriceServiceImpl
 * @Author ChangLu
 * @Date 2021/10/4 13:16
 * @Description TODO
 */
@Service
public class CoursePriceServiceImpl implements CoursePriceService {

    @Autowired
    private CoursePriceMapper coursePriceMapper;
    @Autowired
    private CourseListClient courseListClient;

    @Override
    public CoursePrice getCoursePriceByCourseId(Integer courseId) {
        return coursePriceMapper.selectOne(new LambdaQueryWrapper<CoursePrice>()
                .eq(CoursePrice::getCourseId,courseId)
        );
    }

    @Override
    public List<CourseAndPrice> getAllCourseAndPrice() {
        //远程调用课程服务，查询出所有的课程
        List<Course> courses = courseListClient.getList();
        List<CourseAndPrice> newCourses = new ArrayList<>(courses.size());
        if(!CollectionUtils.isEmpty(courses)){
            for (Course course : courses) {
                CourseAndPrice courseAndPrice = new CourseAndPrice();
                BeanUtils.copyProperties(course, courseAndPrice);
                //本地查询
                CoursePrice price = this.getCoursePriceByCourseId(course.getCourseId());
                courseAndPrice.setPrice(price.getPrice());
                newCourses.add(courseAndPrice);
            }
        }
        return newCourses;
    }
}