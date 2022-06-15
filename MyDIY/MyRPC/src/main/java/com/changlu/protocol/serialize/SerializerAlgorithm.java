package com.changlu.protocol.serialize;

import com.changlu.protocol.serialize.gsonX.MapTypeAdapter;
import com.google.gson.*;
import com.google.gson.internal.bind.ObjectTypeAdapter;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SerializeEnum
 * @Author ChangLu
 * @Date 2022/1/15 15:00
 * @Description 序列化实现类
 */
public enum SerializerAlgorithm implements Serializer {
    JDK{
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            T obj;
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                obj = (T) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("反序列化失败", e);
            }
            return obj;
        }

        @Override
        public <T> byte[] serialize(T object) {
            byte[] bytes;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos);
            ) {
                oos.writeObject(object);
                bytes = baos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("序列化失败", e);
            }
            return bytes;
        }
    },

    Json{
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            Gson gson = getGson();
            String json = new String(bytes, StandardCharsets.UTF_8);
            return gson.fromJson(json, clazz);
        }

        @Override
        public <T> byte[] serialize(T object) {
            Gson gson = getGson();
            String json = gson.toJson(object);
            return json.getBytes(StandardCharsets.UTF_8);
        }

        public Gson getGson() {
            //这行解决字符串转换的问题
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            //下面通过反射修改工厂中的MapTypeAdapter来解决int => 的问题
            try {
                Field factories = Gson.class.getDeclaredField("factories");
                factories.setAccessible(true);
                Object o = factories.get(gson);
                Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
                for (Class c : declaredClasses) {
                    if ("java.util.Collections$UnmodifiableList".equals(c.getName())) {
                        Field listField = c.getDeclaredField("list");
                        listField.setAccessible(true);
                        List<TypeAdapterFactory> list = (List<TypeAdapterFactory>) listField.get(o);
                        int i = list.indexOf(ObjectTypeAdapter.FACTORY);
                        list.set(i, MapTypeAdapter.FACTORY);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gson;
        }
    };

    class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {

        @Override
        public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                String str = json.getAsString();
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }

        @Override             //   String.class
        public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
            // class -> json
            return new JsonPrimitive(src.getName());
        }
    }
}
