package com.changlu.seatauserservice.controller;


import com.changlu.seatauserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/remainbook/{uid}")
    public int getUserRemainBook(@PathVariable("uid")Integer uid) {
        return userService.getUserRemainBook(uid);
    }

    @GetMapping("/minusbook/{uid}")
    public int minusUserBookCount(@PathVariable("uid")Integer uid) {
        return userService.minusUserBookCount(uid);
    }

}

