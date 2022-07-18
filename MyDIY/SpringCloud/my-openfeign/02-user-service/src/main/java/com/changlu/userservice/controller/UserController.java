package com.changlu.userservice.controller;

import com.changlu.userservice.domain.Order;
import com.changlu.userservice.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description:
 * @Author: changlu
 * @Date: 6:50 PM
 */
@RestController
public class UserController {

    /**
     * openfeign来实现代理类注入
     */
    @Autowired
    private UserOrderFeign userOrderFeign;

    /**
     * 总结
     * 浏览器(前端)-------> user-service(/userDoOrder)-----RPC(feign)--->order-service(/doOrder)
     * feign的默认等待时间时1s
     * 超过1s就在直接报错超时
     * @return
     */
    @GetMapping("/userDoOrder")
    public String userDoOrder() {
        System.out.println("用户来访问接口：/userDoOrder");
        //发起远程调用
        String res = userOrderFeign.doOrder();
        return res;
    }

    @GetMapping("/testParam")
    public String testParam() {
        //第一个：url路径携带参数
        String s1 = userOrderFeign.testUrl("changlu", 18);
        System.out.println(s1);

        //第二个：1个请求参数
        String s2 = userOrderFeign.oneParam("changlu");
        System.out.println(s2);

        //第三个：两个请求参数
        String s3 = userOrderFeign.twoParam("changlu", 666);
        System.out.println(s3);

        Order order = Order.builder()
                .name("泡芙")
                .price(1000.0)
                .time(new Date())
                .id(1)
                .build();
        //第四个：请求体
        String s4 = userOrderFeign.oneObj(order);
        System.out.println(s4);

        //第五个：第一个请求体+参数
        String s5 = userOrderFeign.oneObjOneParam(order, "changlu");
        System.out.println(s5);
        return "ok";
    }

    /**
     * Sun Mar 20 10:24:13 CST 2022
     * Mon Mar 21 00:24:13 CST 2022  +- 14个小时
     * 1.不建议单独传递时间参数
     * 2.转成字符串（推荐，比较好的方案，在服务端可以进行format转为date对象）   2022-03-20 10:25:55:213 因为字符串不会改变
     * 3.jdk LocalDate 年月日 没问题    LocalDateTime 会丢失秒
     * 4.改feign的源码
     *
     * @return
     */
    @GetMapping("/testTime")
    public String testTime(){
        //错误示例1：使用new Date();
        userOrderFeign.testTime(new Date());
        //正确方案：服务端的请求接口不应该使用Date来进行接收，尽可能使用字符串、LocalDate来进行传递
        return "ok";
    }

}
