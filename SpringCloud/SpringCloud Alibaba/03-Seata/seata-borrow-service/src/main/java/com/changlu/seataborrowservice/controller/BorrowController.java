package com.changlu.seataborrowservice.controller;


import com.alibaba.fastjson.JSONObject;
import com.changlu.seataborrowservice.service.BorrowService;
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
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/{uid}/{bookId}")
    public JSONObject borrow(@PathVariable("uid") Integer uid, @PathVariable("bookId") Integer bookId) {
        JSONObject object = new JSONObject();
        Boolean res = false;
        try {
            res = borrowService.borrow(uid, bookId);
        }catch (Exception ex) {
            object.put("code", 500);
            object.put("msg", ex.getMessage());
            return object;
        }
        if (res) {
            object.put("code", 200);
            object.put("msg", "借阅成功！");
        }else {
            object.put("code", 500);
            object.put("msg", "借阅失败！");
        }
        return object;
    }

}

