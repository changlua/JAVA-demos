package xyz.changlu.singleton;

import java.lang.reflect.Constructor;

/**
 * @ClassName OwnEnum
 * @Author ChangLu
 * @Date 2021/3/16 21:32
 * @Description TODO
 */
//自定义枚举类来实现单例(解决反射带来的安全问题)
public enum  Student {
    INSTANCE;
}

class test02{
    public static void main(String[] args) throws Exception {
        System.out.println(Student.INSTANCE == Student.INSTANCE);
        //测试反射
        Constructor<Student> con = Student.class.getDeclaredConstructor(String.class,int.class);
        con.setAccessible(true);
        Student ownEnum = con.newInstance();
        System.out.println(ownEnum == Student.INSTANCE);
    }
}
