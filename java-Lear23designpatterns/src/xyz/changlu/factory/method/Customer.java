package xyz.changlu.factory.method;



/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/16 21:14
 * @Description TODO
 */
public class Customer {
    public static void main(String[] args) {
        Car bmw = new BMDFactory().getCar();
        Car porsche = new PorscheFactory().getCar();
        bmw.name();
        porsche.name();
    }
}
