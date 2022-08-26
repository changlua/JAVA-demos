package com.changlu.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DiyPartitionProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.40:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class);

        //指定value值来进行发送
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        String[] values = new String[]{"changlu", "changl", "haha"};
        for (String value: values) {
            kafkaProducer.send(new ProducerRecord<String, String>("first", value)
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
