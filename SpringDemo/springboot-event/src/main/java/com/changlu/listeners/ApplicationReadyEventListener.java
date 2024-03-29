package com.changlu.listeners;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description ApplicationReadyEvent 事件监听器
 * @date 2024/03/28 15:43
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("============>>>>> applicationReadyEvent is trigged");
        System.out.println(event.getTimestamp());
        System.out.println("============>>>>> End");
    }
}
