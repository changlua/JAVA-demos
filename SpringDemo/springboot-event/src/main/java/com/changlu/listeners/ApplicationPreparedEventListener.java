package com.changlu.listeners;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description ApplicationPreparedEvent 事件监听器
 * @date 2024/03/28 15:41
 */
@Component
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        System.out.println("============>>>>> applicationPreparedEvent is trigged");
        System.out.println(event.getTimestamp());
        System.out.println("============>>>>> End");
    }
}
