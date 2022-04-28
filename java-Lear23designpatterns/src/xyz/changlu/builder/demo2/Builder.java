package xyz.changlu.builder.demo2;

/**
 * @ClassName Builder
 * @Author ChangLu
 * @Date 2021/3/16 21:54
 * @Description TODO
 */
//具体工人
public abstract class Builder {
    abstract Builder buildFruit(String msg);
    abstract Builder buildDrink(String msg);
    abstract Builder buildFood(String msg);
    abstract Builder buildTool(String msg);

    abstract Product getProduct();
}
