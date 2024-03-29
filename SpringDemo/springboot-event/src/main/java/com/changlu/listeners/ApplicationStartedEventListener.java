package com.changlu.listeners;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description ApplicationStartedEvent 事件监听器
 * @date 2024/03/28 15:42
 */
@Component
public class ApplicationStartedEventListener implements InitializingBean, ApplicationListener<ApplicationStartedEvent> {


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("============>>>>> applicationStartedEvent is trigged");
        System.out.println(event.getTimestamp());
        System.out.println("============>>>>> End");
    }

    //测试
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("------------>>>>> afterPropertiesSet ");
        System.out.println("------------>>>>> end ");
    }
}
