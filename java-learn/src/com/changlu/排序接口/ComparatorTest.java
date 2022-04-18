package com.changlu.排序接口;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName ComparatorTest
 * @Author ChangLu
 * @Date 4/19/2022 8:42 AM
 * @Description 比较器测试类
 */

//不支持排序的类
class Student1{
    private String name;
    private Integer age;

    public Student1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ComparatorTest {

    public static void main(String[] args) {
        final List<Student1> students = Arrays.asList(new Student1("changlu", 55), new Student1("changlu", 66), new Student1("changlu", 33));
        //若是要排序的类并是支持排序的，那么我们就要自己实现一个比较器
        Collections.sort(students, new Comparator<Student1>() {
            @Override
            public int compare(Student1 o1, Student1 o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(students);
    }

}
