package com.changlu.myhystrix.hystrix.model;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:54 AM
 */
public enum  HystrixStatus {
    //定义三种状态：关闭、开启、半开
    CLOSE,
    OPEN,
    HALF_OPEN
}
