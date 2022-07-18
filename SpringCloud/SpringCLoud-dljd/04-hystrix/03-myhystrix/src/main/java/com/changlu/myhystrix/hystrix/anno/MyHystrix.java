package com.changlu.myhystrix.hystrix.anno;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:59 AM
 */
@Target(ElementType.METHOD) //面向方法
@Retention(RetentionPolicy.RUNTIME)  //运行时
@Documented
@Inherited
public @interface MyHystrix {
}
