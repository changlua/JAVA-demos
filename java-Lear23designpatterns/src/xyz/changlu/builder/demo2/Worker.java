package xyz.changlu.builder.demo2;

/**
 * @ClassName Worker
 * @Author ChangLu
 * @Date 2021/3/16 21:57
 * @Description TODO
 */
public class Worker extends Builder {

    private Product product;

    public Worker() {
        this.product = new Product();
    }

    @Override
    Builder buildFruit(String msg) {
        product.setFruit(msg);
        return this;
    }

    @Override
    Builder buildDrink(String msg) {
        product.setDrink(msg);
        return this;
    }

    @Override
    Builder buildFood(String msg) {
        product.setFood(msg);
        return this;
    }

    @Override
    Builder buildTool(String msg) {
        product.setTool(msg);
        return this;
    }

    @Override
    Product getProduct() {
        return product;
    }
}
