package com.changlu.userservice;

import com.changlu.userservice.controller.UserController;
import com.changlu.userservice.feign.UserOrderFeign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootTest
class UserserviceApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        UserOrderFeign feign = (UserOrderFeign)Proxy.newProxyInstance(UserController.class.getClassLoader(), new Class[]{UserOrderFeign.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String res = null;
                if (method.getName().equals("doOrder")) {
                    //1、获取到对应的path路径
                    // 能去拿到对方的ip和port 并且拿到这个方法上面的注解里面的值 那么就完事了
                    GetMapping annotation = method.getAnnotation(GetMapping.class);
                    String[] paths = annotation.value();
                    String path = paths[0];
                    //2、根据对应的的方法来获取到相应的class字节码
                    Class<?> aclass = method.getDeclaringClass();
                    //3、获取到对应feign类的注解，取得对应的服务名
                    FeignClient fannoation = aclass.getAnnotation(FeignClient.class);
                    String serviceName = fannoation.value();
                    //4、拼接服务地址
                    String url = "http://" + serviceName + path;
                    //5、发送请求
                    res = restTemplate.getForObject(url, String.class);
                }
                return res;
            }
        });
        System.out.println(feign.doOrder());
    }

}
