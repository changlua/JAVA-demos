package com.changlu.自定义类加载器;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/19/2022 10:22 AM
 * @Description 自定义类加载器
 */

/**
 * 若是想要自己直接加载自定义的class类，那么可以通过自己实现ClassLoader的findClass方法来直接加载class文件，绕过双亲委派机制
 */
class MyClassLoader extends ClassLoader{

    private String pathName = "E:\\自学历程\\Gitee仓库\\demo-exer\\java-learn\\out\\production\\java-jvm\\com\\changlu\\自定义类加载器\\";

    private String packageName = "com.changlu.自定义类加载器.";

    public MyClassLoader(String pathName, String packageName) {
        if (pathName != null || packageName != null) {
            this.pathName = pathName;
            this.packageName = packageName;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //getResource("")是获取到当前输出out及完整包路径
//        String fileName = MyClassLoader.class.getResource("").getPath().substring(1) + name + ".class";
        //这里通过绝对路径来进行表示
        String fileName = this.pathName + name + ".class";
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            Files.copy(Paths.get(fileName), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final byte[] bytes = bos.toByteArray();
        //若是class类中有包名，那么这里就需要自己来进行指定
        return defineClass(this.packageName + name, bytes, 0, bytes.length);//此时并不会进行类的初始化
    }
}

public class Test {

    public static void main(String[] args) throws Exception{
        //测试加载当前工程目录下的Person
        Class<?> person = new MyClassLoader(null, null).findClass("Person");
        final Object o = person.newInstance();
        //测试当前java.lang包下的String
        final Class<?> string = new MyClassLoader("E:\\自学历程\\Gitee仓库\\demo-exer\\java-learn\\out\\production\\java-jvm\\java\\lang\\", "java.lang.").findClass("String");
        string.newInstance();
    }

}
