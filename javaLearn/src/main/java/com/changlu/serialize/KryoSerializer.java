package com.changlu.serialize;

import com.changlu.serialize.pojo.RPCResponse;
import com.changlu.serialize.pojo.User;
import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.esotericsoftware.kryo.kryo5.objenesis.strategy.StdInstantiatorStrategy;
import com.esotericsoftware.kryo.kryo5.util.DefaultInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName KryoSerializer
 * @Author ChangLu
 * @Date 6/14/2022 8:33 PM
 * @Description Kryo序列化工具
 */
public class KryoSerializer implements Serializer{

    // 由于Kryo是线程不安全的，所以我们这里使用ThreadLocal来解决线程安全问题
    public static ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(()->{
            Kryo kryo = new Kryo();
            kryo.setReferences(true);//检测循环依赖，默认值为true，避免版本变化显示设置
            //方式一：设置无需注册，那么之后就无需对需要进行序列号的类进行注册（性能略差）
//            kryo.setRegistrationRequired(false);//默认值为true，避免版本变化显示设置
            ((DefaultInstantiatorStrategy)kryo.getInstantiatorStrategy())
                    .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());//设置默认的实例化器
             //方式二：由于默认是需要进行注册的，若是不设置为false，那么就需要进行手动注册class类
            kryo.register(User.class);
            kryo.register(RPCResponse.class);
            return kryo;
    });

    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)
        ) {
            Kryo kryo = kryoThreadLocal.get();
            //进行序列化
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed");
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            Object obj = kryo.readObject(input, clazz);
            kryoThreadLocal.remove();
            return clazz.cast(obj);
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed");
        }
    }

}
