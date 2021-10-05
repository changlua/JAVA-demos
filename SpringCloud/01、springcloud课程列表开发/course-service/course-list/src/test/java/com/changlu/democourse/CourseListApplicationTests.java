package com.changlu.democourse;

import com.changlu.democourse.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseListApplicationTests {

    @Autowired
    private CourseService courseService;

    @Test
    void contextLoads() {
        System.out.println(courseService.getCourseList());
    }

}
