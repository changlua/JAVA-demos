package com.changlu.productor.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName MsgSender
 * @Author ChangLu
 * @Date 2021/9/25 13:42
 * @Description 发送消息到指定交换机
 */
@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static String EXCHANGE_NAME = "bootExchange";

    //发送信息的routingkey=>"cat.red"
    public void send1(){
        String routingKey = "cat.red";
        String msg = "this is my message,routingkey is "+routingKey;
        //交换机名称、routingkey以及发送的信息
        amqpTemplate.convertAndSend(EXCHANGE_NAME,routingKey,msg);
        System.out.println("已成功发送信息："+msg);
    }

    //发送信息的routingkey=>"dog.red"
    public void send2(){
        String routingKey = "dog.red";
        String msg = "this is my message,routingkey is "+routingKey;
        //交换机名称、routingkey以及发送的信息
        amqpTemplate.convertAndSend(EXCHANGE_NAME,routingKey,msg);
        System.out.println("已成功发送信息："+msg);
    }


}