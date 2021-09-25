package No3四种交换机.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @ClassName EmitLog
 * @Author ChangLu
 * @Date 2021/9/25 7:10
 * @Description 生产者：交换机类型fanout。绑定交换机LOGS，无routingkeys
 */
public class EmitLog {

    //核心：指定交换机名称
    private static String EXCHANGE_NAME = "LOGS";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.118.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            //绑定一个交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            //向指定的交换机发送消息
            String msg = "this is a log info!";
            //第二个参数是routingkey，第三个参数是基本属性
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息发送成功！");
        }catch (Exception e){
            System.out.println("消息发送失败，失败原因："+e.getMessage());
        }
        Optional.ofNullable(channel).ifPresent(EmitLog::close);
        Optional.ofNullable(connection).ifPresent(EmitLog::close);

    }

    //反射进行资源关闭
    public static <T> void close(T t){
        Class<?> aClass = t.getClass();
        Optional.ofNullable(t).ifPresent(c-> {
            try {
                Method closeMethod = aClass.getDeclaredMethod("close");
                closeMethod.setAccessible(true);
                closeMethod.invoke(t, null);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}