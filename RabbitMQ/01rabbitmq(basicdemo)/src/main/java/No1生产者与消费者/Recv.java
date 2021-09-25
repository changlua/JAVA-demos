package No1生产者与消费者;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Recv
 * @Author ChangLu
 * @Date 2021/9/23 21:27
 * @Description 消费者：直接从消息队列Hello中拿到消息，若是有多个服务是按照数量进行分配的。
 */
public class Recv {

    //队列名称
    private static String QUEUE_NAME = "Hello";

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
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //6、核心：接收消息。额外说明：由于发送方发送的消息没有消费者接受就会一直存储在队列里，一旦有消费方就会进行消费对应队列中的内容！
        //①队列的名称：指定队列
        //②是否是自动消息确认，意思就是我拿到这个消息之后告诉你发送的人我收到了，有签收的机制
        //③回调函数：获取到这个消息之后进行消费
        channel.basicConsume(QUEUE_NAME,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //获取到消息，body就是对应传输过来的消息体
                String message = new String(body,"UTF-8");
                System.out.println("收到消息："+message);
            }
        });
        //注意：这里就不进行关闭了，因为这里直接关闭连接，上面的回调函数很有可能在进行回调前就结束程序，导致还没有消费程序就over了。
        //上面的回调函数是异步的！！！
    }

}