package xyz.changlu.builder.demo2;

/**
 * @ClassName Product
 * @Author ChangLu
 * @Date 2021/3/16 21:55
 * @Description TODO
 */
//具体产品：一种搭配
public class Product {
    private String fruit = "葡萄";
    private String drink = "牛奶";
    private String food = "意面";
    private String tool = "手套";

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    @Override
    public String toString() {
        return "Product{" +
                "fruit='" + fruit + '\'' +
                ", drink='" + drink + '\'' +
                ", food='" + food + '\'' +
                ", tool='" + tool + '\'' +
                '}';
    }
}
