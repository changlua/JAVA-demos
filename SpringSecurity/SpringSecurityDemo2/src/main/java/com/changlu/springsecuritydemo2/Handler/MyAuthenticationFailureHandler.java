package com.changlu.springsecuritydemo2.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyAuthenticationFailureHandler
 * @Author ChangLu
 * @Date 3/22/2022 5:33 PM
 * @Description 自定义失败处理器
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String,Object> result = new HashMap<>(2);
        result.put("msg","登录失败");
        result.put("code",500);
        if (exception.getCause() != null){
            result.put("err des",exception.getCause().getMessage());
        }
        response.setContentType("application/json;charset=UTF-8");
        String json = new ObjectMapper().writeValueAsString(result);
        response.getWriter().write(json);
    }
}