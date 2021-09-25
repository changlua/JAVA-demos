package com.changlu;

import com.changlu.mapper.OrderMapper;
import com.changlu.mapper.UserMapper;
import com.changlu.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.ChangLuImportBeanDefinitionRegister;
import org.mybatis.spring.ChangLuScan;
import org.mybatis.spring.MapperFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName ChangLuApplication
 * @Author ChangLu
 * @Date 2021/8/22 22:48
 * @Description TODO
 */
@ComponentScan("com.changlu")
@ChangLuScan("com.changlu.mapper")
public class ChangLuApplication {

    //将SqlSessionFactory进行注入到Spring容器中
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");//读取配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ChangLuApplication.class);
        applicationContext.refresh();

        //测试
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.test();

    }
}