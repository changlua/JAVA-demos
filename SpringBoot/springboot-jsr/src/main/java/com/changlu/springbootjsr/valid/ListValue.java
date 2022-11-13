package com.changlu.springbootjsr.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: 指定限定值
 * @Author: changlu
 * @Date: 1:29 PM
 */
@Documented
//设置自定义的注解校验器
@Constraint(validatedBy = {ListValueConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ListValue {
    //默认的message值为resources资源目录下的ValidationMessages.properties文件中对应的key值
    String message() default "{com.changlu.springbootjsr.valid.ListValue.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    //自定义传入属性
    int[] vals() default {};
}
