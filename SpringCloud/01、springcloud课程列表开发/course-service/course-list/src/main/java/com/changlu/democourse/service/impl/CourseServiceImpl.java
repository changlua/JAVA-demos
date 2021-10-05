package com.changlu.democourse.service.impl;

import com.changlu.democourse.mapper.CourseMapper;
import com.changlu.democourse.pojo.Course;
import com.changlu.democourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CourseServiceImpl
 * @Author ChangLu
 * @Date 2021/10/4 12:45
 * @Description TODO
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourseList() {
        return courseMapper.getCourseList();
    }
}