package com.changlu.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * @Description: 自定义全局过滤器
 * @Author: changlu
 * @Date: 9:17 PM
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求体以及请求对象
        ServerHttpRequest request = exchange.getRequest();
        // HttpServletRequest  这个是web里面的
        // ServerHttpRequest  webFlux里面 响应式里面的
        ServerHttpResponse response = exchange.getResponse();
        //通过请求对象可以拿到请求的一系列内容
        String path = request.getURI().getPath();//uri路径
        System.out.println("path:" + path);
        HttpHeaders headers = request.getHeaders();//请求头
        System.out.println("headers:" + headers);
        String name = request.getMethod().name();//请求方法名，也就是对应ip:port/xxx，这个/xxx
        System.out.println("method name:"+ name);
        String ip = request.getHeaders().getHost().getHostString();//获取到ip主机名
        System.out.println("ip:" + ip);

        //来进行测试响应数据
        // 用了微服务 肯定是前后端分离的 前后端分离 一般前后通过 json
        // {"code":200,"msg":"ok"}
        //1、设置响应头
        response.getHeaders().set("content-type", "application/json;charset=utf-8");
        //2、响应结果集封装
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.UNAUTHORIZED.value());
        result.put("msg", "暂未授权");
        ObjectMapper objectMapper = new ObjectMapper();//jackson工具类
        byte[] data = new byte[0];
        try {
            data = objectMapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //通过使用buffer工厂类来将其转为一个数据包（底层是基于netty，该对象底层是nio的bytebuffer）
        DataBuffer wrap = response.bufferFactory().wrap(data);
//        return response.writeWith(Mono.just(wrap));
        //放行过滤器
        return chain.filter(exchange);
    }

    //越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
