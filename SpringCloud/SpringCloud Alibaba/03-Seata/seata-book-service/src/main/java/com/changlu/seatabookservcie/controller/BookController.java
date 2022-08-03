package com.changlu.seatabookservcie.controller;


import com.changlu.seatabookservcie.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/remain/{id}")
    public int bookRemain(@PathVariable("id") Integer id) {
        return bookService.bookRemain(id);
    }

    @GetMapping("/minus/{id}")
    public int minusBookRemain(@PathVariable("id") Integer id) {
        return bookService.minusBookRemain(id);
    }

}

