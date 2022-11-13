package com.changlu.springbootjsr.exception;

import com.changlu.springbootjsr.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 异常控制器捕捉
 * @Author: changlu
 * @Date: 11:17 AM
 */
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.changlu.springbootjsr.controller")
//替代上方的两个注解
@RestControllerAdvice(basePackages = "com.changlu.springbootjsr.controller")
public class ExceptionControllerAdvice {

    //捕捉参数校验异常（精确）
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}， 异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return R.error(400, "参数校验异常").put("data", errorMap);
    }

    //大范围(没有指定某个class的额外异常捕捉)
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("错误", throwable);
        return R.error(500, "系统异常，请联系管理员");
    }

}
