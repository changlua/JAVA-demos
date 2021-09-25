package No1生产者与消费者;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Send
 * @Author ChangLu
 * @Date 2021/9/23 21:15
 * @Description 生产者：自定义队列Hello，直接将消息发送到队列中
 */
public class Send {

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
        //①队列名称；
        //②是否需要持久，例如服务器重启该队列是否需要存在，测试环境一般不需要所以设置为false，生产环境根据需求来定
        //③是否独有的意思，表明该队列是否只能给我这个连接使用。
        //④是否需要自动删除，在该队列没有使用的情况下自动删除
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //6、发布消息(发送出去！)
        String message = "Hello,world!";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
        System.out.println("发送了消息："+message);
        //7、关闭连接
        channel.close();
        connection.close();
    }

}