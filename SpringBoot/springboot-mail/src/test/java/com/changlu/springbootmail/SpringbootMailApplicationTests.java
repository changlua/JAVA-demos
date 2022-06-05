package com.changlu.springbootmail;

import com.changlu.springbootmail.utils.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMailApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MailService mailService;

    @Test
    void mailtest1() throws Exception {
        //1、发送普通邮件
//        mailService.sendMail();
        //2、发送带附件文件
        mailService.sendMailIncludeFile();
    }
}
