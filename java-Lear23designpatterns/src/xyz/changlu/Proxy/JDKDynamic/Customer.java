package xyz.changlu.Proxy.JDKDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName Csutomer
 * @Author ChangLu
 * @Date 2021/3/18 20:45
 * @Description TODO
 */
class MyInvocationHandler implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("query".equals(method.getName())){
            System.out.println("调用了query()方法");
        }else if("update".equals(method.getName())){
            System.out.println("调用了update()方法");
        }else if("delete".equals(method.getName())){
            System.out.println("调用了delete()方法");
        }
        return null;
    }
}


//使用JDK静态代理(加强接口)
public class Customer {
    public static void main(String[] args) {
        //最重要的传入接口类数组(含你的接口)，以及自定义的InvocationHandler
        Service service = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Service.class}, new MyInvocationHandler());
        service.query();
        service.update();
        service.delete();
    }
}
