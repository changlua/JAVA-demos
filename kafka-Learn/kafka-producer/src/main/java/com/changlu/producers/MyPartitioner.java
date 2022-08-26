package com.changlu.producers;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @Description:
 * @Author: changlu
 * @Date: 7:14 PM
 */

//自定义分区
public class MyPartitioner implements Partitioner {

    //关键重写该方法
    /**
     * 返回信息对应的分区（这就是核心的自定义方法）
     * @param topic 主题
     * @param key 消息key
     * @param keyBytes 消息key序列化的字节数组
     * @param value 消息value
     * @param valueBytes 消息value序列化的字节数组
     * @param cluster 集群元数据可查看分区信息
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //若是value中有changlu那么就发送给分区1，没有发送给0
        String msgValue = value.toString();
        int partition = 0;
        if (msgValue.contains("changlu")) {
            partition = 1;
        }
        //集群的指定主题的分区数量
        System.out.println(cluster.partitionCountForTopic(topic));
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
