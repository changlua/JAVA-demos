package xyz.changlu.strategy.demo1;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/20 22:48
 * @Description TODO
 */
//测试类
public class Customer {
    public static void main(String[] args) {
        Context context = new Context();
        context.strategyMethod();

        //更改指定策略之后再次使用
        context.setStrategy(new ConcreteStrategyB());
        context.strategyMethod();
    }
}
