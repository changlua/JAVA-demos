package com.changlu.server.netty;

/**
 * @ClassName ServiceProvider
 * @Author ChangLu
 * @Date 5/31/2022 5:31 PM
 * @Description TODO
 */

import java.util.Map;

/**
 * 存放服务接口名与服务端对应的实现类
 * 服务启动时要暴露其相关的实现类
 */
public class ServiceProvider {

    private Map<String, Object> interfaceProvider;

    public ServiceProvider(Map<String, Object> interfaceProvider) {
        this.interfaceProvider = interfaceProvider;
    }

    /**
     * 存放一个实现类：一个实现类可能包含多个接口，一同存储
     */
    public void provideServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            this.interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return this.interfaceProvider.get(interfaceName);
    }
}
