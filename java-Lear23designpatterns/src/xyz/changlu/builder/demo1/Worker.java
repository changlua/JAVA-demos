package xyz.changlu.builder.demo1;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

/**
 * @ClassName Worker
 * @Author ChangLu
 * @Date 2021/3/16 21:39
 * @Description TODO
 */
//具体建造者：负责各个步骤以及产品提供
public class Worker extends Builder{

    //需要获取一个产品
    private Product product;

    public Worker(){
        this.product = new Product();
    }

    @Override
    void builderA() {
        product.setBuilderA("打地基");
        System.out.println("打地基");
    }

    @Override
    void builderB() {
        product.setBuilderB("拉线");
        System.out.println("拉线");
    }

    @Override
    void builderC() {
        product.setBuilderC("搭房子");
        System.out.println("搭房子");
    }

    @Override
    void builderD() {
        product.setBuilderB("砌墙");
        System.out.println("砌墙");
    }

    @Override
    Product getProduct() {
        return product;
    }
}
