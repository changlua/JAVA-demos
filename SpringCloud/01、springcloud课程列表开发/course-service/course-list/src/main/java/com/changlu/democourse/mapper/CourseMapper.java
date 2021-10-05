package com.changlu.democourse.mapper;

import com.changlu.democourse.pojo.Course;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CourseMapper
 * @Author ChangLu
 * @Date 2021/10/4 12:46
 * @Description TODO
 */
@Repository
public interface CourseMapper {

    @Select("select * from course")
    List<Course> getCourseList();

}