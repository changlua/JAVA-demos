package com.changlu.springboottest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: changlu
 * @Date: 11:19 PM
 */
@Service
public class ServiceB {

//    @Lazy
//    @Autowired
    private ServiceA serviceA;

    @Lazy
    @Autowired
    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

//    @Autowired
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
