package com.changlu.productor;

import com.changlu.productor.config.MsgSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductorApplicationTests {

    @Autowired
    private MsgSender msgSender;

    @Test
    void sendMsg1() {
        msgSender.send1();
        msgSender.send2();
    }

}
