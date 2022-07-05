package com.changlu.ribbonconsumer.config;


import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/**
 * @Description: 我的自定义负载均衡器
 * @Author: changlu
 * @Date: 10:21 PM
 */
public class MyRule implements IRule {
    @Override
    public Server choose(Object key) {
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {

    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return null;
    }
}
