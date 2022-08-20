package com.changlu.annontions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: API的幂等性接口
 * @Author: changlu
 * @Date: 10:02 AM
 */
@Target(ElementType.METHOD)  //使用于方法
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface ApiIdempotent {
}
