package com.changlu;

import com.changlu.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author changlu
 * @version 1.0
 * @description 手动获取spring容器，接着发布事件
 * @date 2024/03/28 14:44
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //发布事件，指定的事件为：EventTest
        context.publishEvent(new MyEvent(context, "changlu"));
    }
}
