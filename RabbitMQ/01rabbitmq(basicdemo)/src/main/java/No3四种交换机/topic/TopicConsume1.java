package No3四种交换机.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TopicConsume1
 * @Author ChangLu
 * @Date 2021/9/25 11:13
 * @Description TODO
 */
public class TopicConsume1 {

    //交换机名称
    private static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        TopicProduce.Util.doBefore();
        Channel channel = TopicProduce.Util.channel;
        //指定交换机为topic
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();
        //指定routingkeys，可搭配*或#进行匹配
        String bindingKey = "*.orange.*";
        channel.queueBind(queueName,EXCHANGE_NAME,bindingKey);//绑定队列、交换机以及routingkey
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("收到消息："+msg);
            }
        });
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