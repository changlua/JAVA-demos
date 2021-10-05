package com.changlu.democourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.changlu.democourse.mapper")
public class CourseListApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseListApplication.class, args);
    }

}
