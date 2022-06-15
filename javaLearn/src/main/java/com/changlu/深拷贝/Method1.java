package com.changlu.深拷贝;

import java.io.*;

/**
 * @ClassName Method1
 * @Author ChangLu
 * @Date 4/19/2022 8:58 AM
 * @Description 方式一：采用序列化方式进行深拷贝
 */

class Wallet implements Serializable{
    private String name;
    private Long money;

    public Wallet(String name, Long money) {
        this.name = name;
        this.money = money;
    }
}

class Person implements Serializable {
    private String name;
    private Wallet wallet;

    public Person(String name, Wallet wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}

public class Method1 {

    public static void main(String[] args) throws Exception {
        Person person = new Person("changlu", new Wallet("包包1", 1000L));
        System.out.println(person);
        //中转站
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oops = new ObjectOutputStream(baos);
        oops.writeObject(person);//写入到中转站中
        final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Person person1= (Person) ois.readObject();//通过读取字符流的方式来进行深拷贝
        System.out.println(person1);

    }

}
