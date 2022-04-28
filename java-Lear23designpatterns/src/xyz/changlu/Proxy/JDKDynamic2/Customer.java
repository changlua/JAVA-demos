package xyz.changlu.Proxy.JDKDynamic2;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/18 15:11
 * @Description TODO
 */

//自定义InvocationHandler
class MyInvocationHandler implements InvocationHandler {

    private Service service;

    public MyInvocationHandler(Service service){
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行日志记录......");
        //执行ServiceImpl实现类中的方法
        method.invoke(service, args);
        return null;
    }
}

//测试类
public class Customer {
    public static void main(String[] args) {
        //将要增强的实现类传入到自定义的InvocationHandler中
        MyInvocationHandler invocationHandler = new MyInvocationHandler(new ServiceImpl());
        //传入参数：类加载器、指定接口的class类数组(可指定多个)、自定义InvocationHandler
        Service service = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Service.class}, invocationHandler);
        service.query();
        service.delete();
        service.update();
    }

    //生成代理类文件
    @Test
    public void test() throws Exception {
        //主要是第二个参数：其为要实现的接口(数组形式传递,这里传入Service接口)
        byte[]classFile = ProxyGenerator.generateProxyClass("Proxy0",new Class[]{Service.class});
        FileOutputStream fos =new FileOutputStream(new File("Proxy0.class"));
        fos.write(classFile);
        fos.flush();
        fos.close();
    }
}
