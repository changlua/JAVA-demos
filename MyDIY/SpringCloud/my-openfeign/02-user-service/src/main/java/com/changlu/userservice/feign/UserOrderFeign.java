package com.changlu.userservice.feign;

import com.changlu.userservice.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description: 远程调用器
 * @Author: changlu
 * @Date: 6:52 PM
 */
@FeignClient(value = "order-service")//value表示服务名
public interface UserOrderFeign {

    /**
     * 你需要调用哪个controller  就写它的方法签名
     * 方法签名(就是包含一个方法的所有的属性)
     */
    @GetMapping("/doOrder")
    public String doOrder();


    //*****请求参数测试案例*****
    //url参数：PathVariable
    @GetMapping("testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name, @PathVariable("age") Integer age);

    //请求参数：?xx=xx
    @GetMapping("oneParam")
    public String oneParam(@RequestParam(required = false) String name);

    //请求参数：?xx=xx&xx=xx
    @GetMapping("twoParam")
    public String twoParam(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age);

    //请求体参数
    @PostMapping("oneObj")
    public String oneObj(@RequestBody Order order);

    //请求体 + 请求参数url中的xx=xx
    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name);

    @GetMapping("testTime")
    public String testTime(@RequestParam Date date);

}
