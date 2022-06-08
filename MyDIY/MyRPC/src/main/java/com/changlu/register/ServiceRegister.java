package com.changlu.register;

import java.net.InetSocketAddress;

/**
 * @ClassName ServiceRegister
 * @Author ChangLu
 * @Date 6/4/2022 12:30 PM
 * @Description 服务注册接口
 */
public interface ServiceRegister {

    void register(String serviceName, InetSocketAddress serverAddress);

    InetSocketAddress serviceDiscovery(String serviceName);

}
