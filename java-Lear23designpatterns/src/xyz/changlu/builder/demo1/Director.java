package xyz.changlu.builder.demo1;

/**
 * @ClassName Director
 * @Author ChangLu
 * @Date 2021/3/16 21:41
 * @Description TODO
 */
//指挥者：指挥工人做事
public class Director {

    //通过传入参数的工人，来指挥他做事
    public static Product build(Builder builder){
        builder.builderA();
        builder.builderB();
        builder.builderC();
        builder.builderD();
        return builder.getProduct();
    }

}
