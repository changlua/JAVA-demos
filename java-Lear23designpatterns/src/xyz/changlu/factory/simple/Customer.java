package xyz.changlu.factory.simple;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/16 21:21
 * @Description TODO
 */
public class Customer {
    public static void main(String[] args) {
        //通过简单工厂来获取指定实例
        Car bmw = CarFactory.getCar("宝马");
        Car porsche = CarFactory.getCar("保时捷");
        bmw.name();
        porsche.name();
    }
}
