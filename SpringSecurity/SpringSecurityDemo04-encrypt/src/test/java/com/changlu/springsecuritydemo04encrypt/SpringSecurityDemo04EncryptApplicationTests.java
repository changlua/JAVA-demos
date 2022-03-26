package com.changlu.springsecuritydemo04encrypt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityDemo04EncryptApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));
        System.out.println(bCryptPasswordEncoder.matches("123", "$2a$10$D2wuJaYJLwTdQrPKev7SuufhfRAr.DahZ.euMRjg3edKAp2p0qHUS"));
    }

}
