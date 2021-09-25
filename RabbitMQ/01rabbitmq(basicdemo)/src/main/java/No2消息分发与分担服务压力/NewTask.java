package No2消息分发与分担服务压力;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName NewTask
 * @Author ChangLu
 * @Date 2021/9/24 21:49
 * @Description 生产者：自定义队列名称，没有使用交换机，直接发送到队列Task中
 */
public class NewTask {
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
        //6、发布消息(发送出去！)
        for (int i = 0; i < 15; i++) {
            String message = "消息任务"+i;
            if(i%2==0){
                message+= "!";
            }
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("已发送信息：" + message);
        }
        //7、关闭连接
        channel.close();
        connection.close();
    }
}