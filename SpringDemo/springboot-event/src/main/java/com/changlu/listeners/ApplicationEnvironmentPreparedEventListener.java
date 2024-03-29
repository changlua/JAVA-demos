package com.changlu.listeners;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description ApplicationEnvironmentPreparedEvent 事件监听器
 * @date 2024/03/28 15:37
 */
@Component
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println("============>>>>> ApplicationEnvironmentPreparedEvent is trigged");
        System.out.println(event.getTimestamp());
        System.out.println("============>>>>> End");
    }
}
