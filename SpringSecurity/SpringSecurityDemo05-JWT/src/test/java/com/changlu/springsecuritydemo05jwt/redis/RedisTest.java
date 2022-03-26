package com.changlu.springsecuritydemo05jwt.redis;

import com.changlu.springsecuritydemo05jwt.utils.RedisCache;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName RedisTest
 * @Author ChangLu
 * @Date 3/25/2022 11:33 AM
 * @Description Redis测试
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisCache redisCache;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User{
        private String username;
        private Integer sex;
        private String phone;
    }

    @Test
    public void contextLoads(){
        testGet();
    }

    public void testSet(){
        User user = new User("changlu", 1, "134558");
        redisCache.setCacheObject("changlu", user);
        System.out.println(redisCache.getCacheObject("changlu").toString());
    }

    public void testGet(){
        System.out.println(redisCache.getCacheObject("login:1").toString());
    }

}