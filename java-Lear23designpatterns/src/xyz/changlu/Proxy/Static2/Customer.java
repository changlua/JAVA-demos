package xyz.changlu.Proxy.Static2;

import org.junit.Test;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/17 23:58
 * @Description TODO
 */
public class Customer {
    @Test
    public void test01(){
        //被代理类测试
        Service service = new ServiceImpl();
        service.query();
        service.update();
        service.query();
    }

    @Test
    public void test02(){
        //增强ServiceImpl被代理类：使用代理类来对被代理类进行方法增强
        Service service = new ServiceProxy(new ServiceImpl());
        service.query();
        service.update();
        service.query();
    }
}
