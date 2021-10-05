package com.changlu.courselist.controller;

import com.changlu.courselist.client.CourseListClient;
import com.changlu.courselist.pojo.CourseAndPrice;
import com.changlu.courselist.pojo.CoursePrice;
import com.changlu.courselist.service.CoursePriceService;
import com.changlu.democourse.pojo.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CoursePriceController
 * @Author ChangLu
 * @Date 2021/10/4 13:17
 * @Description TODO
 */
@RestController
@RequestMapping("/courseprice")
public class CoursePriceController {

    @Autowired
    private CoursePriceService coursePriceService;
    //远程调用动态代理类注入
    @Autowired
    private CourseListClient courseListClient;

    @GetMapping("/list/{id}")
    public CoursePrice getList(@PathVariable("id")Integer id){
        return  coursePriceService.getCoursePriceByCourseId(id);
    }

    @GetMapping("/test")
    public List<Course> testFeignCourseService(){
        return courseListClient.getList();
    }

    @GetMapping("/courseprice")
    public List<CourseAndPrice> getAllCourseAndPrice(){
        return coursePriceService.getAllCourseAndPrice();
    }

}