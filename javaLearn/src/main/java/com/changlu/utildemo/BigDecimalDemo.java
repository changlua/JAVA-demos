package com.changlu.utildemo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Description: BigDecimal的案例
 * @Author: changlu
 * @Date: 3:25 PM
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        //计算浮点数的错误示例：
        float a = 2.0f - 1.9f;
        float b = 1.8f - 1.7f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999905
        System.out.println(a == b);// false
        //使用BigDeimcal来进行浮点数计算
        BigDecimal a1 = new BigDecimal("2.0");
        BigDecimal b2 = new BigDecimal("1.9");
        BigDecimal c2 = new BigDecimal("1.8");
        BigDecimal x = a1.subtract(b2);
        BigDecimal y = b2.subtract(c2);
        System.out.println(x);//0.1
        System.out.println(y);//0.1
        System.out.println(Objects.equals(x, y));//true
    }
}
