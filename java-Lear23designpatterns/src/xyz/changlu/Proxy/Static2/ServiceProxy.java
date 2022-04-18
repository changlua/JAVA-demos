package xyz.changlu.Proxy.Static2;

import java.lang.reflect.Proxy;
import java.util.logging.Logger;

/**
 * @ClassName ServiceProxy
 * @Author ChangLu
 * @Date 2021/3/17 23:54
 * @Description TODO
 */
public class ServiceProxy implements Service{

    private Service service;

    public ServiceProxy(Service service){
        this.service = service;
    }

    @Override
    public void query() {
        System.out.println("执行日志记录...");
        service.query();
    }

    @Override
    public void update() {
        System.out.println("执行日志记录...");
        service.update();
    }

    @Override
    public void delete() {
        System.out.println("执行日志记录...");
        service.delete();
    }
}
