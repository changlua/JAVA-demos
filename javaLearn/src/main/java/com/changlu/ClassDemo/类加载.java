package com.changlu.ClassDemo;

/**
 * @ClassName 类加载
 * @Author ChangLu
 * @Date 4/19/2022 8:08 AM
 * @Description TODO
 */

class Student {
    private static final String name = "小明";
    public static final Integer num = 15;//底层走的是Integer.valueOf()，自然也就会初始化

    static {
        System.out.println("类加载");
    }

    private static class LazyHolder{
        static final Student INSTANCE = new Student();

        static {
            System.out.println("Lazy Holder 初始化....");
        }
    }

    public static Student getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static String getName(){
        return name;
    }
}

public class 类加载 {

    public static void main(String[] args) {
//        System.out.println(Student.num);
//        System.out.println(Integer.valueOf(20));
        //不会导致LazyHolder初始化
//        System.out.println(new Student());
        System.out.println(Student.getInstance());//利用静态内部类中不初始化的意义来进行单例
    }

}
