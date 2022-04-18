package xyz.changlu.strategy.demo1;

/**
 * @ClassName ConcreteStrategy
 * @Author ChangLu
 * @Date 2021/3/20 22:44
 * @Description TODO
 */
//具体策略实现类A
public class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体策略类A实现算法....");
    }
}
