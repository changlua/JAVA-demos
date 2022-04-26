package com.changlu.排序接口;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName ComparableAndComparator
 * @Author ChangLu
 * @Date 4/19/2022 8:26 AM
 * @Description 排序接口以及
 */
//实现了Comparable就是支持排序的类
class Student implements Comparable<Student>{
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;//若是当前元素 > 传入参数的元素，返回结果为正数(正数就是升序)
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ComparableTest {

    public static void main(String[] args) {
        final List<Student> students = Arrays.asList(new Student("changlu", 55), new Student("changlu", 66), new Student("changlu", 33));
        Collections.sort(students);
        System.out.println(students);
    }

}
