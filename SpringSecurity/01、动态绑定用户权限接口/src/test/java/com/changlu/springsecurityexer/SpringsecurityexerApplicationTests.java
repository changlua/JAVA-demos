package com.changlu.springsecurityexer;

import com.changlu.springsecurityexer.entity.UserEntity;
import com.changlu.springsecurityexer.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringsecurityexerApplicationTests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String password = "changlu123";
        String encodePassword = passwordEncoder.encode(password);//随机盐加密后的密码
        System.out.println("输出:" + encodePassword);
        String ep = "";
        boolean flag = passwordEncoder.matches(password, encodePassword);
        if (flag) {//判断 matches()方法的值去判断密码是否正确
            System.out.println("验证成功！！");
        } else {
            System.out.println("验证失败！！");
        }
        System.out.println("长度:" + encodePassword.length());
    }

}
