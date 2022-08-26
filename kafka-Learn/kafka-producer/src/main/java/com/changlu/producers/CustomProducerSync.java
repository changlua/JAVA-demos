package com.changlu.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Description: 自定义生产者
 * @Author: changlu
 * @Date: 10:27 AM
 */
public class CustomProducerSync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.40:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 5; i++) {
            //同步发送（消息发送操作是同步的）
            kafkaProducer.send(new ProducerRecord<String, String>("first", "changlu" + i), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //xxx处理
                }
            }).get();
        }

        kafkaProducer.close();
    }

}
