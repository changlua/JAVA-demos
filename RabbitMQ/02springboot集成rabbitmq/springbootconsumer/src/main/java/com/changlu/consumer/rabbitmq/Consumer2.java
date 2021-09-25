package com.changlu.consumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer1
 * @Author ChangLu
 * @Date 2021/9/25 13:52
 * @Description queue2对应routingkey=>*.red
 */
@Component
@RabbitListener(queues = "queue2")
public class Consumer2 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("queue2收到消息："+msg);
    }

}