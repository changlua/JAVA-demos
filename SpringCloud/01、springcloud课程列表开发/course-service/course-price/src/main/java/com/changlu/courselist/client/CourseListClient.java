package com.changlu.courselist.client;

import com.changlu.democourse.pojo.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName CourseListClient
 * @Author ChangLu
 * @Date 2021/10/4 16:36
 * @Description 远程调用
 */
@FeignClient(value = "course-list",fallback = CourseListClientHystrix.class)
@Primary
public interface CourseListClient {

    @GetMapping("/course/list")
    List<Course> getList();

}
