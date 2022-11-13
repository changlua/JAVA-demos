package com.changlu.springbootjsr.controller;

import com.changlu.springbootjsr.entity.BrandEntity;
import com.changlu.springbootjsr.utils.R;
import com.changlu.springbootjsr.valid.AddGroup;
import com.changlu.springbootjsr.valid.UpdateGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 商品控制器
 * @Author: changlu
 * @Date: 10:30 AM
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    //P2.1章节  未在@Valid后添加BindingResult
//    @PostMapping
//    public R updateBrand(@Valid @RequestBody BrandEntity brandEntity) {
//        return R.ok().put("data", brandEntity);
//    }

    //P2.2-2.3章节  无全局异常捕捉
    //添加接口
//    @PostMapping("/add")
//    public R addeBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brandEntity, BindingResult result) {
//        //若是实体类校验出现错误
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            //获取校验的所有错误结果
//            result.getFieldErrors().forEach((item)->{
//                //通过FieldError 获取到错误提示
//                String message = item.getDefaultMessage();
//                //获取错误属性的名称
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.error(400, "提交的数据不合法").put("data", map);
//        }
//        return R.ok().put("data", brandEntity);
//    }

    //更新接口
//    @PostMapping
//    public R updateBrand(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brandEntity, BindingResult result) {
//        //若是实体类校验出现错误
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            //获取校验的所有错误结果
//            result.getFieldErrors().forEach((item)->{
//                //通过FieldError 获取到错误提示
//                String message = item.getDefaultMessage();
//                //获取错误属性的名称
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.error(400, "提交的数据不合法").put("data", map);
//        }
//        return R.ok().put("data", brandEntity);
//    }

    //P3.1、支持全局异常捕捉的接口
    //添加接口
    @PostMapping("/add")
    public R addeBrand(@Validated({AddGroup.class}) @RequestBody BrandEntity brandEntity) {
        return R.ok().put("data", brandEntity);
    }

    //更新接口
    @PostMapping
    public R updateBrand(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brandEntity) {
        return R.ok().put("data", brandEntity);
    }

}
