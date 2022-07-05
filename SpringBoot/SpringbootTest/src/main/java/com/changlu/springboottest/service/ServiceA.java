package com.changlu.springboottest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: changlu
 * @Date: 11:18 PM
 */
@Service
public class ServiceA {

//    @Lazy
//    @Autowired
    private ServiceB serviceB;

    @Lazy
    @Autowired
    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

//    @Autowired
//    public void setServiceB(ServiceB serviceB) {
//        this.serviceB = serviceB;
//    }
}
