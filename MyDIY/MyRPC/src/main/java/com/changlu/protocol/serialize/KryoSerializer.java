package com.changlu.protocol.serialize;

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
            kryo.setRegistrationRequired(false);
            kryo.setReferences(true);//检测循环依赖，默认值为true，避免版本变化显示设置
            ((DefaultInstantiatorStrategy)kryo.getInstantiatorStrategy())
                    .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());//设置默认的实例化器
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
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
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

    //单例模式
    public static class KryoSerializerHolder {
        private static KryoSerializer instance = new KryoSerializer();
    }

    public static KryoSerializer getInstance() {
        return KryoSerializerHolder.instance;
    }

}
