package No3四种交换机.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ReceiveLog
 * @Author ChangLu
 * @Date 2021/9/25 7:23
 * @Description 消费者：交换机类型logs，无绑定routingkeys
 */
public class ReceiveLog {

    private static String EXCHANGE_NAME = "LOGS";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.118.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //绑定指定的交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //获取到指定队列的类型，并绑定交换机与队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,EXCHANGE_NAME,"");//第三个参数为routingkey
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