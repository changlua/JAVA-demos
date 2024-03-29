package com.changlu;
import org.springframework.context.ApplicationEvent;

/**
 * @description  MyEvent 事件
 * @author changlu
 * @date 3/28/2024 3:10 PM
 * @version 1.0
 */
public class MyEvent extends ApplicationEvent {

    private String name;

    public MyEvent(Object source, String  name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
