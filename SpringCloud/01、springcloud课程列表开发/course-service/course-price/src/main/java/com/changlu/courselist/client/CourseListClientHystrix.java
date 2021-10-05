package com.changlu.courselist.client;

import com.changlu.democourse.pojo.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CourseListClientHystrix
 * @Author ChangLu
 * @Date 2021/10/4 20:30
 * @Description 断路器，对于服务不可用或其他情况进行统一的返回值返回
 */
@Component
public class CourseListClientHystrix implements CourseListClient{

    @Override
    public List<Course> getList() {
        Course course = new Course();
        course.setId(0);
        course.setCourseId(0);
        course.setName("Java从入门到精通");
        course.setValid(0);
        return Collections.singletonList(course);
    }

}