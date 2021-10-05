package com.changlu.courselist;

import com.changlu.courselist.mapper.CoursePriceMapper;
import com.changlu.courselist.pojo.CoursePrice;
import com.changlu.courselist.service.CoursePriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoursePricePriceApplicationTests {

    @Autowired
    private CoursePriceService coursePriceService;

    @Test
    void contextLoads() {
        System.out.println(coursePriceService.getCoursePriceByCourseId(362));
    }

}
