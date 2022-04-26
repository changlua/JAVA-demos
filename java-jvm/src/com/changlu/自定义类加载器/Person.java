package com.changlu.自定义类加载器;

/**
 * @ClassName Person
 * @Author ChangLu
 * @Date 4/19/2022 10:26 AM
 * @Description TODO
 */
public class Person {
    static {
        System.out.println("我是在当前工程中的Person，我正在初始化....");
    }
}
