package com.changlu.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Description: 自定义生产者
 * @Author: changlu
 * @Date: 10:27 AM
 */
public class CustomProducerCallback {

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.40:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

//        for (int i = 0; i < 5; i++) {
////            //1、在send中进行回调函数编写
//////            kafkaProducer.send(new ProducerRecord<String, String>("first", "changlu" + i)
////            //2、指明partition（这里分区为1，key为空字符串）
////            kafkaProducer.send(new ProducerRecord<String, String>("first", 1, "", "changlu" + i)
////                    , new Callback() {
////                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
////                    if (e == null) {
////                        System.out.println(String.format("主题：%s, 分区：%s", recordMetadata.topic(), recordMetadata.partition()));
////                    }else {
////                        e.printStackTrace();
////                    }
////                }
////            });
////        }

        //3、分别发送key为a,b,f的三条消息
        String[] arr = new String[]{"a", "b", "f"};
        for (String c : arr) {
            kafkaProducer.send(new ProducerRecord<String, String>("first", c, "changlu")
                    , new Callback() {
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if (e == null) {
                                System.out.println(String.format("主题：%s, 分区：%s", recordMetadata.topic(), recordMetadata.partition()));
                            }else {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        kafkaProducer.close();
    }

}
