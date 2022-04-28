package xyz.changlu.strategy.demo1;

/**
 * @ClassName Context
 * @Author ChangLu
 * @Date 2021/3/20 22:46
 * @Description TODO
 */
//指定环境类
public class Context {
    private Strategy strategy;//具体策略

    public Context(){
        //默认使用策略A
        this.strategy = new ConcreteStrategyA();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    //执行指定策略方法
    public void strategyMethod(){
        //通过调用指定的策略执行，可通过set/get方法更改策略
        strategy.strategyMethod();
    }

}
