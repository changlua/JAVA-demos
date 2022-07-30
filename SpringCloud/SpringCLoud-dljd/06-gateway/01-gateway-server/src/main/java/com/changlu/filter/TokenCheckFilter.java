package com.changlu.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: token检查过滤器
 * @Author: changlu
 * @Date: 9:25 AM
 */
@Component
public class TokenCheckFilter implements GlobalFilter, Ordered {

    private static final String token = "700d7a8d-262a-447a-8254-9dd9ead6a0e2";

    private static final List<String> WHITE_PATH = Arrays.asList("/doLogin");

    /**
     * 流程：1、路径检测（是否放行）。2、请求头token获取。3、校验：放行or直接响应
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //放行一些公开接口
        String path = request.getURI().getPath();
        if (WHITE_PATH.contains(path)) {
            return chain.filter(exchange);
        }
        //从请求头中获取到Authorization
        List<String> authorization = request.getHeaders().get("Authorization");
        if (!ObjectUtils.isEmpty(authorization)) {
            String token = authorization.get(0);
            //去掉前缀"bearer "
            token = token.replaceFirst("Bearer ", "");
            //token校验，成功放行（实际上会进行token解析取到uuid来从redis中获取，这里简单来表示一下）
            if (TokenCheckFilter.token.equals(token)) {
                return chain.filter(exchange);
            }
        }
        //失败进行错误响应
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type", "application/json;charset=utf-8");
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
        DataBuffer wrap = response.bufferFactory().wrap(data);
        return response.writeWith(Mono.just(wrap));
    }

    //     * 这个顺序 最好在0附近  -2 -1 0 1
    @Override
    public int getOrder() {
        return 1;
    }
}
