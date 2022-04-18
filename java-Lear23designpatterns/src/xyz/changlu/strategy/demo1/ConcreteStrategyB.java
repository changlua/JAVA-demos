package xyz.changlu.strategy.demo1;

/**
 * @ClassName ConcreteStrategyB
 * @Author ChangLu
 * @Date 2021/3/20 22:45
 * @Description TODO
 */
//具体策略实现类B
public class ConcreteStrategyB implements Strategy {

    @Override
    public void strategyMethod() {
        System.out.println("具体策略实现类B执行算法....");
    }
}
