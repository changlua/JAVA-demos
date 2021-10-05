package com.changlu.democourse.controller;

import com.changlu.democourse.pojo.Course;
import com.changlu.democourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CourseController
 * @Author ChangLu
 * @Date 2021/10/4 13:03
 * @Description 课程控制器
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public List<Course> getList(){
        return  courseService.getCourseList();
    }

}