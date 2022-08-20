package com.changlu.interceptors;

import com.changlu.annontions.ApiIdempotent;
import com.changlu.utils.ServletUtils;
import com.changlu.utils.TokenUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description: aoi幂等性接口拦截器
 * @Author: changlu
 * @Date: 10:07 AM
 */
@Component
public class ApiIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtilService tokenUtilService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验是否有执行方法
        if (!(handler instanceof HandlerMethod)) {
            return true;//若没有对应的方法执行器，就直接放行
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent annotation = method.getAnnotation(ApiIdempotent.class);
        //若是没有幂等性注解直接放行
        if (annotation != null) {
            //解析对应的请求头
            String token = request.getHeader("token");
            if (ObjectUtils.isEmpty(token)) {
                throw new RuntimeException("请携带token令牌");
//                ServletUtils.renderString(response, "请携带token令牌");
//                return false;
            }
            //若是校验失败直接进行响应
            if (!tokenUtilService.validToken(token, "changlu")) {
                throw new RuntimeException("重复提交失败！");
//                ServletUtils.renderString(response, "重复提交失败！");
//                return false;
            }
        }
        return true;
    }
}
