package No3四种交换机.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ReceiveLogs
 * @Author ChangLu
 * @Date 2021/9/25 9:50
 * @Description 消费者：绑定交换机direct_LOGS，并且设置三个routingkeys：info、debug、warning
 */
public class ReceiveLogs1 {

    private static String EXCHANGE_NAME = "direct_LOGS";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.118.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //绑定指定的交换机类型——direct
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //生成一个随机的临时的queue
        String queueName = channel.queueDeclare().getQueue();
        //一个交换机同时绑定三个routingkeys
        channel.queueBind(queueName,EXCHANGE_NAME,"info");
        channel.queueBind(queueName,EXCHANGE_NAME,"debug");
        channel.queueBind(queueName,EXCHANGE_NAME,"warning");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("接收到消息："+msg);
            }
        };
        //进行消费，需要指定队列名称
        channel.basicConsume(queueName,true,consumer);
    }
}