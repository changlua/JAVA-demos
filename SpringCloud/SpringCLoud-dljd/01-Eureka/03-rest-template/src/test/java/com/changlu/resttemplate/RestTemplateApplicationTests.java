package com.changlu.resttemplate;

import com.changlu.resttemplate.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RestTemplateApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        //访问百度页面
        String url = "https://www.baidu.com";
        String content = restTemplate.getForObject(url, String.class);
        System.out.println(content);
    }

    @Test
    void testGet() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/testGet?name=cxs";
        //        String result = restTemplate.getForObject(url, String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        // http:// 协议 (规范 接头暗号)
        // 请求头 请求参数 .. 响应头 响应状态码 报文 ....
        System.out.println(responseEntity);
    }

    @Test
    void testPostJson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/testPost1";
        //封装对象
        User user = new User("changlu", 22, 1000D);
        //发送POST，而且是JSON参数,在web里面默认会使用jackson，会将对象转为字符串
        String content = restTemplate.postForObject(url, user, String.class);
        System.out.println(content);
    }

    @Test
    void testPost() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/testPost2";
        //对于表单参数，使用LinkedMultiValueMap来进行封装
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", "changlu");
        map.add("age", 26);
        map.add("price", 80080);
        String content = restTemplate.postForObject(url, map, String.class);
        System.out.println(content);
    }

}
