package com.changlu.listeners;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description ApplicationFailedEvent 事件监听器
 * @date 2024/03/28 15:44
 */
@Component
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        System.out.println("============>>>>> ApplicationFailedEvent is trigged");
        System.out.println(event.getTimestamp());
        System.out.println("============>>>>> End");
    }
}
