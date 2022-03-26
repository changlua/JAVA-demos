package com.changlu.springsecuritydemo2.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyAuthenticationSuccessHandler
 * @Author ChangLu
 * @Date 3/22/2022 5:27 PM
 * @Description 自定义认证成功处理器
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> result = new HashMap<>(3);
        result.put("msg","登录成功");
        result.put("status",200);
        result.put("Authentication",authentication);
        response.setContentType("application/json;charset=UTF-8");
        String json = new ObjectMapper().writeValueAsString(result);
        response.getWriter().write(json);
    }
}