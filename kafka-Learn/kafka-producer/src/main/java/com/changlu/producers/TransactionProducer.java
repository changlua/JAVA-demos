package com.changlu.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Description:
 * @Author: changlu
 * @Date: 7:55 PM
 */
public class TransactionProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.40:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //核心1：设置事务id(任意)
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction_id_0");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        //自定义回调函数
        Callback callback = new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println(String.format("主题：%s, 分区：%s", metadata.topic(), metadata.partition()));
                }else {
                    exception.printStackTrace();
                }
            }
        };
        // 初始化事务
        kafkaProducer.initTransactions();
        // 开启事务
        kafkaProducer.beginTransaction();
        //发送两台消息
        try {
            kafkaProducer.send(new ProducerRecord<>("first", 1, "1", "changlu"), callback);
            kafkaProducer.send(new ProducerRecord<>("first", 0, "1", "changlu2"), callback);
            //制造异常
//            int i = 1 / 0;
            // 提交事务
            kafkaProducer.commitTransaction();
        }catch (Exception e) {
            e.printStackTrace();
            kafkaProducer.abortTransaction();
        }finally{
            kafkaProducer.close();
        }

    }
}
