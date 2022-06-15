package com.changlu.serialize;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @Description: ProtoStuffSer序列化工具
 * @Author: changlu
 * @Date: 11:33 AM
 */
public class ProtoStuffSerializer implements Serializer{

    //DEFAULT_BUFFER_SIZE：512
    //每次序列化时使用缓冲区
    private static final LinkedBuffer BUFFER = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    public byte[] serialize(Object obj) {
        Class<?> clazz = obj.getClass();
        Schema schema = RuntimeSchema.getSchema(clazz);
        byte[] bytes;
        try {
            //序列化
            bytes = ProtostuffIOUtil.toByteArray(obj, schema, BUFFER);
        }finally {
            BUFFER.clear();
        }
        return bytes;
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        //反序列化
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }
}
