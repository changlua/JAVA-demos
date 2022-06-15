package com.changlu.serialize;

import com.changlu.serialize.pojo.RPCResponse;
import com.changlu.serialize.pojo.User;
import com.changlu.serialize.protobuf.DemoModel;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * @Description: 序列号测试工具
 * @Author: changlu
 * @Date: 9:31 AM
 */
public class SerializerTest {

    @Test
    public void test(){
        //测试对象
        RPCResponse<User> rpcResponse = new RPCResponse<>();
        rpcResponse.setData(new User("changlu", 123));
        //测试kryo工具
        testSerialize(new KryoSerializer(), rpcResponse);
        //测试JDK原生序列化工具
        testSerialize(new JdkSerializer(), rpcResponse);
        //测试ProtoBuf
        testProtobufSerialzize();
    }


    public <T> void testSerialize(Serializer serializer, T t) {
        System.out.println(String.format("=====开始序列化:%s=====", serializer.getClass()));
        System.out.println("开始进行序列化");
        long startTime = System.nanoTime();
        //序列化
        byte[] data = serializer.serialize(t);
        long endTime = System.nanoTime();
        System.out.println("  序列化时间为：" + (endTime - startTime) / 1000000000.0 + "秒");
        System.out.println("  序列化后的内容为：" + new String(data));
        System.out.println("  序列化后的长度为：" + data.length);
        System.out.println("开始进行反序列化");
        startTime = System.nanoTime();
        //反序列化
        System.out.println("  反序列化后得到的对象为：" + serializer.deserialize(data, t.getClass()));
        endTime = System.nanoTime();
        System.out.println("  反序列化时间为：" + (endTime - startTime) / 1000000000.0 + "秒");
        System.out.println(String.format("=====结束序列化:%s=====", serializer.getClass()) + "\n");
    }

    public void testProtobufSerialzize(){
        //准备实体类
        DemoModel.User.Builder userBuilder = DemoModel.User.newBuilder();
        userBuilder.setAge(18);
        userBuilder.setName("changlu");
        DemoModel.User user = userBuilder.build();
        System.out.println("=====开始序列化:Protobuf=====");
        System.out.println("开始进行序列化");
        long startTime = System.nanoTime();
        //序列化
        byte[] data = user.toByteArray();
        long endTime = System.nanoTime();
        System.out.println("  序列化时间为：" + (endTime - startTime) / 1000000000.0 + "秒");
        System.out.println("  序列化后的内容为：" + new String(data));
        System.out.println("  序列化后的长度为：" + data.length);
        System.out.println("开始进行反序列化");
        startTime = System.nanoTime();
        //反序列化
        try {
            System.out.println("  反序列化后得到的对象为：" + DemoModel.User.parseFrom(data));
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException("Serialization failed");
        }
        endTime = System.nanoTime();
        System.out.println("  反序列化时间为：" + (endTime - startTime) / 1000000000.0 + "秒");
        System.out.println("=====结束序列化:Protobuf=====" + "\n");
    }

}
