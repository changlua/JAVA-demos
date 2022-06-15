package com.changlu.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description: Hessian序列化
 * @Author: changlu
 * @Date: 12:38 PM
 */
public class HessianSerializer implements Serializer{
    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();){
            HessianOutput hessianOutput = new HessianOutput(baos);
            //序列化
            hessianOutput.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed");
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);){
            HessianInput hessianInput = new HessianInput(bais);
            //反序列化
            return clazz.cast(hessianInput.readObject(clazz));
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed");
        }
    }
}
