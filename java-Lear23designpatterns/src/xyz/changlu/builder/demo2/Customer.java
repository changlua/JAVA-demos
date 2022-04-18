package xyz.changlu.builder.demo2;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/16 23:06
 * @Description TODO
 */
public class Customer {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Product product = worker.buildDrink("可乐")
                .getProduct();
        System.out.println(product);
    }
}
