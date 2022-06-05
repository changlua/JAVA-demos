package com.changlu.service;


import com.changlu.pojo.Blog;

/**
 * @ClassName BlogService
 * @Author ChangLu
 * @Date 5/31/2022 2:47 PM
 * @Description 博客业务接口
 */
public interface BlogService {
    Blog getBlogById(Integer id)throws Exception;
}
