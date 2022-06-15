package com.changlu.ClassDemo;

import java.lang.reflect.Method;

/**
 * @ClassName 泛型擦除
 * @Author ChangLu
 * @Date 4/18/2022 11:58 PM
 * @Description TODO
 */

interface info<T> {
    T info(T var);
}

class InfoImpl implements info<Integer>{

    @Override
    public Integer info(Integer var) {
        return var;
    }
}

public class 泛型擦除 {
    public static void main(String[] args) {
        final InfoImpl info = new InfoImpl();
        for (Method declaredMethod : info.getClass().getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
            System.out.println(declaredMethod.getReturnType().getName());
        }
    }
}
