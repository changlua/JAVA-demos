package com.changlu.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Description: 自定义生产者
 * @Author: changlu
 * @Date: 10:27 AM
 */
public class CustomProducer {

    public static void main(String[] args) {
        //1、创建kafka的生产者配置对象
        Properties properties = new Properties();

        //2、添加对象配置参数：bootstrap.servers、key与value序列化器
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.40:9092");
        //key,value序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //3、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //4、send方法进行发送
        for (int i = 0; i < 5; i++) {
            //没有指明key与partition
            kafkaProducer.send(new ProducerRecord<String, String>("first", "changlu" + i));
        }

        //5、关闭资源
        kafkaProducer.close();
    }

}
