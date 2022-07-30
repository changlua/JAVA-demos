//package com.changlu.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Description: IP检查过滤器
// * @Author: changlu
// * @Date: 8:41 AM
// */
//@Component
//public class IPCheckFilter implements GlobalFilter, Ordered {
//
//    /**
//     * 网关的并发比较高 不要再网关里面直接操作mysql
//     * 后台系统可以查询数据库 用户量 并发量不大
//     * 如果并发量大 可以查redis 或者 在内存中写好
//     */
//    private static final List<String> BLACK_LIST = Arrays.asList("127.0.0.1", "192.168.1.1", "localhost");
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //获取到请求对象化
//        ServerHttpRequest request = exchange.getRequest();
//        String ip = request.getHeaders().getHost().getHostString();
//        //若是在集合中出现该ip，那么此时就拦截响应（一般黑名单可以存储在数据库中也可以存储的redis里）
//        if (!BLACK_LIST.contains(ip)) {
//            chain.filter(exchange);
//        }
//        //若是存在就进行拦截，并响应
//        ServerHttpResponse response = exchange.getResponse();
//        response.getHeaders().set("content-type", "application/json;charset=utf-8");
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", 438);
//        result.put("msg", "你已被拉黑，无法访问");
//        ObjectMapper objectMapper = new ObjectMapper();
//        byte[] data = new byte[0];
//        try {
//            data = objectMapper.writeValueAsBytes(result);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        DataBuffer wrap = response.bufferFactory().wrap(data);
//        return response.writeWith(Mono.just(wrap));
//    }
//
//    @Override
//    public int getOrder() {
//        return 1;
//    }
//}
