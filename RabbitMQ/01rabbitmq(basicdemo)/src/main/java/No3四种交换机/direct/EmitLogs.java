package No3四种交换机.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName EmitLogs
 * @Author ChangLu
 * @Date 2021/9/25 9:44
 * @Description 生产者：绑定交换机direct_LOGS，发送四个消息，每个消息匹配一个routingkeys(info、debug、error、warning)
 */
public class EmitLogs {

    //核心：指定交换机名称，需要跟之前的不一样
    private static String EXCHANGE_NAME = "direct_LOGS";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.118.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //绑定一个Direct类型交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String msg = "sended msg";
        //发送三条消息，每个消息绑定对应的routingkey，发送给指定的一个交换机
        String INFO_MESSAGE = "info-"+msg;
        channel.basicPublish(EXCHANGE_NAME,"info",null,INFO_MESSAGE.getBytes(StandardCharsets.UTF_8));
        System.out.println("成功发送消息："+INFO_MESSAGE);
        String DEBUG_MESSAGE = "debug-"+msg;
        channel.basicPublish(EXCHANGE_NAME,"debug",null,DEBUG_MESSAGE.getBytes(StandardCharsets.UTF_8));
        System.out.println("成功发送消息："+DEBUG_MESSAGE);
        String WARNING_MESSAGE = "warning-"+msg;
        channel.basicPublish(EXCHANGE_NAME,"warning",null,WARNING_MESSAGE.getBytes(StandardCharsets.UTF_8));
        System.out.println("成功发送消息："+WARNING_MESSAGE);
        String ERROR_MESSAGE = "warning-"+msg;
        channel.basicPublish(EXCHANGE_NAME,"error",null,ERROR_MESSAGE.getBytes(StandardCharsets.UTF_8));
        System.out.println("成功发送消息："+ERROR_MESSAGE);

        channel.close();
        connection.close();

    }
}