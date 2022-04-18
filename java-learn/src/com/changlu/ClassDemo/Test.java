package com.changlu.ClassDemo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/19/2022 9:37 AM
 * @Description TODO
 */
public class Test {
    private String name;

    String getName(){
        return name;
    }

    public static void main(String[] args) {
        System.out.println(new ConcurrentHashMap<String,Object>(1, 0.65f, 1));
    }
}
