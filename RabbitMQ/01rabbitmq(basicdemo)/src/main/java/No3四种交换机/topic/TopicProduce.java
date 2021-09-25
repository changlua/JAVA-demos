package No3四种交换机.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TopicProduce
 * @Author ChangLu
 * @Date 2021/9/25 10:57
 * @Description 生产者：交换机类型为topic
 */
public class TopicProduce {

    private static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Util.doBefore();
        Channel channel = Util.channel;
        //指定交换机为topic
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //定义关键字，不同的消息在发送时都指定routingKeys
        String[] routingKeys = new String[]{
            "quick.orange.rabbit","quick.orange.fox","quick.brown","quick.orange.male.rabbit",
            "lazy.orange.elephant","lazy.brown.fox","lazy.pink.rabbit","lazy.orange.male.rabbit",
            "orange"
        };
        for (String routingKey : routingKeys) {
            String msg = "信息内容："+routingKey;
            //发送
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("已发送"+msg);
        }
        Util.close();
    }


    static class Util{
        private static Connection connection = null;
        public static Channel channel = null;

        public static void  doBefore() throws IOException, TimeoutException {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.118.128");
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("password");
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        }

        public static void close() throws IOException, TimeoutException {
            if(channel!=null){
                channel.close();
            }
            if(connection!=null){
                connection.close();
            }
        }

    }
}