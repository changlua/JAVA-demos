package xyz.changlu.template;

/**
 * @ClassName ConcreteTemplate
 * @Author ChangLu
 * @Date 2021/3/19 21:41
 * @Description TODO
 */
//具体模板类：实现具体的核心业务功能
public class ConcreteTemplate extends AbstractTemplate {
    @Override
    public void abstractMethod1() {
        System.out.println("执行query()查询操作");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("执行update()更新操作");
    }
}
