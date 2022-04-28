package xyz.changlu.template;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/19 21:42
 * @Description TODO
 */
//测试类
public class Customer {
    public static void main(String[] args) {
        AbstractTemplate template = new ConcreteTemplate();
        template.templateMethod();//执行模板方法
    }
}
