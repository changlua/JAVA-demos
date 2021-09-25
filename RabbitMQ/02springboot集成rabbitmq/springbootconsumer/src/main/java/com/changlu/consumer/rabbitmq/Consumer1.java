package com.changlu.consumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer1
 * @Author ChangLu
 * @Date 2021/9/25 13:52
 * @Description queue1对应routingkey=>cat.red
 */
@Component
@RabbitListener(queues = "queue1")
public class Consumer1 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("queue1收到消息："+msg);
    }

}