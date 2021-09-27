package com.changlu.springbootdemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootdemoApplicationTests {

    @Test
    void contextLoads() {
    }

}

@Data
class User{
    private String name;
    @JsonIgnore
    private String password;
    private String sex;
    private Integer age;

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setName("changlu");
        user.setPassword("123456");
        user.setSex("男");
        //转JSON
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(user));
    }
}
