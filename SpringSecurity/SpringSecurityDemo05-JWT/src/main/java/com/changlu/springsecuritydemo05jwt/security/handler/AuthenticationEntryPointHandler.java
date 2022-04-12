package com.changlu.springsecuritydemo05jwt.security.handler;

import com.alibaba.fastjson.JSON;
import com.changlu.springsecuritydemo05jwt.domain.ResponseResult;
import com.changlu.springsecuritydemo05jwt.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthenticationEntryPointHandler
 * @Author ChangLu
 * @Date 3/25/2022 2:37 PM
 * @Description 自定义认证异常处理器（未认证，401）
 */
@Component
@Slf4j
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ResponseResult<String> responseResult = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "当前未认证" + "，原因：" + authException.getMessage(), null);
        String json = JSON.toJSONString(responseResult);
        ServletUtils.renderString(response,json);
    }
}
