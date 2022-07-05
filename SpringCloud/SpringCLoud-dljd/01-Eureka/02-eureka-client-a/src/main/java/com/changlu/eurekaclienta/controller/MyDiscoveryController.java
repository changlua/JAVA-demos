package com.changlu.eurekaclienta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 我的服务注册发现
 * @Author: changlu
 * @Date: 10:04 PM
 */
@RestController
public class MyDiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;//这个接口是springcloud提供的，然后eureka提供了对应的实现类

    @GetMapping("/find")
    public String find(String serviceId) {
        //调用服务发现
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        instances.forEach(System.out::println);
        return instances.toString();
    }

}
