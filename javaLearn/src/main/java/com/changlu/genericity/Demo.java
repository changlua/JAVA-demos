package com.changlu.genericity;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:43 PM
 */
public class Demo {
}

class User<T> {
    public void test(T t) {
        System.out.println(t);
    }

    public static <T> void test1(T t) {

    }
}

class Demo1<T> extends User<T> {

}