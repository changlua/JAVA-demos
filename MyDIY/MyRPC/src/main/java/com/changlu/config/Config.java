package com.changlu.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName Config
 * @Author ChangLu
 * @Date 5/31/2022 10:40 PM
 * @Description TODO
 */
public class Config {

    public static byte serializerType;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("E:\\自学历程\\Gitee仓库\\demo-exer\\MyDIY\\MyRPC\\src\\main\\resources\\application.properties"));
            serializerType = Byte.valueOf(properties.getProperty("serializerType"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(serializerType);
    }


}
