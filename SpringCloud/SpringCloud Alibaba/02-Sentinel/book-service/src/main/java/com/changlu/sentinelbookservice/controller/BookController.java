package com.changlu.sentinelbookservice.controller;

import com.changlu.sentinelbookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: changlu
 * @Date: 12:39 PM
 */
@RestController
public class BookController {

    @GetMapping("/list")
    public List<String> list() {
        List<String> books = Arrays.asList("西游记", "水浒传", "红楼梦");
        //同样也去进行调用service服务
        System.out.println(bookService.getUserLikeBook(1L));
        return books;
    }

    @Autowired
    private BookService bookService;

    @GetMapping("/book/like/{id}")
    public String getUserLikeBook(@PathVariable("id")Long id) {
        String book = bookService.getUserLikeBook(id);
        return book;
    }

}
