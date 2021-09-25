package No2消息分发与分担服务压力;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName SolveTasks
 * @Author ChangLu
 * @Date 2021/9/24 21:52
 * @Description 消费者：直接从消息队列Task中获取信息，并且这里是进行优化的能够分担服务压力(按压力分配任务数量)。
 */
public class SolveTasks {
    //队列名称
    private static String QUEUE_NAME = "Task";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2、设置rabbitmq地址
        connectionFactory.setHost("192.168.118.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        //3、建立连接
        Connection connection = connectionFactory.newConnection();
        //4、获取信道
        Channel channel = connection.createChannel();
        //5、声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //1、设置公平
        channel.basicQos(1);
        //2、设置第二个参数为false，表示要进行手动消息签收
        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //获取到消息，body就是对应传输过来的消息体
                String message = new String(body,"UTF-8");
                execTask(message);
                System.out.println("消息："+message+"处理完毕！");
                //3、执行签收确认操作，此时再向rabbitmq请求消息。（第二个参数表示是否帮多个进行同时确认）
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }


    public static void execTask(String msg){
        char[] chars = msg.toCharArray();
        System.out.println("开始处理消息："+msg);
        for (char aChar : chars) {
            if(aChar == '!'){
                try {
                    //睡眠一秒，表示用来执行业务
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}