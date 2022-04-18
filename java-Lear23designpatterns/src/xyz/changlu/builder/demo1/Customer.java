package xyz.changlu.builder.demo1;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/16 21:42
 * @Description TODO
 */
//顾客类(测试)
public class Customer {
    public static void main(String[] args) {
        //创建一个指挥者
        Director director = new Director();
        //调用建造方法，传入参数为指定的建造者
        Product product = director.build(new Worker());
        //打印一下产品信息
        System.out.println(product);
    }
}
