package com.changlu.listeners;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description ApplicationStartedEvent 事件监听器
 * @date 2024/03/28 15:35
 */
@Component
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("============>>>>> applicationStartingEvent is trigged");
        System.out.println(event.getTimestamp());
        System.out.println("============>>>>> End");
    }
}
