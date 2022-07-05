package com.changlu.ribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:56 PM
 */
@RestController
public class ConsumerController {

    //方式一：增强版的RestTemplate（附带负载均衡）
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/testRibbon")
    public String testRibbon(String serviceId) {
        String url = "http://" + serviceId + "/hello";
        String content = restTemplate.getForObject(url, String.class);
        // 如果你想使用原生的restTemplate 就需要重新创建一个对象
//        RestTemplate myRest = new RestTemplate();
//        String forObject = myRest.getForObject("http://localhost:8888/aaa", String.class);
        return content;
    }

    //方式二：使用ribbon提供的客户端来进行负载均衡获取服务
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/testRibbon2")
    public String testRibbon2(String serviceId) {
        //根据服务来进行选择一个实例
        ServiceInstance choose = loadBalancerClient.choose(serviceId);
        return choose.toString();
    }

}
