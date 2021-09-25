package com.changlu.productor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TopicRabbitConfig
 * @Author ChangLu
 * @Date 2021/9/25 13:37
 * @Description Topic类型交换机配置
 */
@Configuration
public class TopicRabbitConfig {

    //配置队列
    @Bean
    public Queue queue1(){
        return new Queue("queue1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("queue2");
    }

    //配置交换机
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("bootExchange");
    }

    @Bean
    public Binding bindingExchangeMessage1(Queue queue1,TopicExchange exchange){
        return BindingBuilder.bind(queue1).to(exchange).with("cat.red");
    }

    @Bean
    public Binding bindingExchangeMessage2(Queue queue2,TopicExchange exchange){
        return BindingBuilder.bind(queue2).to(exchange).with("*.red");
    }

}