package com.changlu.test;

import com.changlu.config.AliyunOssConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OssTestApplicationTests {

    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    @Test
    void contextLoads() {
        System.out.println(aliyunOssConfig);
    }

    public static void main(String[] args) {
        String url = "http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/zhifeng/b8809d28-82ec-4b06-af5f-8d3d7a16107c.jpg";
        final String fileName = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(fileName);
    }

}
