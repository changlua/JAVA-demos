package com.changlu.springboottest;

import com.changlu.springboottest.service.ServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootTestApplicationTests {

    @Autowired
    private ServiceA serviceA;

    @Test
    void contextLoads() {
        System.out.println(serviceA);
    }

}
