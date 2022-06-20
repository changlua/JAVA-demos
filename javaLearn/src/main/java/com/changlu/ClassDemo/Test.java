package com.changlu.ClassDemo;


/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/19/2022 9:37 AM
 * @Description TODO
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("666");
        }
    }
}

class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        defineClass();
        return super.loadClass(name);
    }
}