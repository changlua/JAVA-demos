package com.changlu;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author changlu
 * @version 1.0
 * @description MyEvent事件Listener监听器
 * @date 2024/03/28 15:06
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        //可以拿到这个对象，可以使用到其中的相应的事件对象
        System.out.println("名字叫做：" + event.getName());
    }
}
