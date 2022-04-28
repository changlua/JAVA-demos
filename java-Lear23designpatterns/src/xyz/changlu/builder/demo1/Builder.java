package xyz.changlu.builder.demo1;

/**
 * @ClassName Builder
 * @Author ChangLu
 * @Date 2021/3/16 21:36
 * @Description TODO
 */
//抽象工人类
public abstract class Builder {

    abstract void builderA();
    abstract void builderB();
    abstract void builderC();
    abstract void builderD();

    //产生产品
    abstract Product getProduct();
}
