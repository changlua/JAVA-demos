package com.changlu.springbootjsr.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: @ListValue注解限制校验器
 * @Author: changlu
 * @Date: 1:36 PM
 */
//第一个泛型指的是注解，第二个泛型指的是校验什么类型的数据（一般指的是我们标注在某个类型的数据上）
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set = new HashSet<>();

    //初始化操作：constraintAnnotation是我们真实标注在某个属性上的完整注解含设置的属性内容
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    //判断校验是否成功
    //属性一：Integer value，这个值就是我们标注的属性也就是请求体传来的值，实际上我们会判断这个value是否为0或者1
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
