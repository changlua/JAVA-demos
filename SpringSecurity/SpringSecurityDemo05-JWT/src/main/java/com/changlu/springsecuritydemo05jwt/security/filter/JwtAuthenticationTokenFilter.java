package com.changlu.springsecuritydemo05jwt.security.filter;

import com.changlu.springsecuritydemo05jwt.domain.LoginUser;
import com.changlu.springsecuritydemo05jwt.utils.JwtUtil;
import com.changlu.springsecuritydemo05jwt.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtAuthenticationFilter
 * @Author ChangLu
 * @Date 3/25/2022 3:33 PM
 * @Description 自定义认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1、获取token
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            filterChain.doFilter(request,response);//放行，当前未认证走到最后的拦截器会对其进行异常返回，走我们自己定义的认证失败处理器
            return;
        }
        //2、解析token，获取用户id
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token已失效或非法");
        }
        //3、从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("login:" + userId);
        // 若是用户信息不存在，抛出异常重新登录
        if (ObjectUtils.isEmpty(loginUser)) {
            throw new RuntimeException("用户认证已失效，请重新登录");
        }
        //4、认证成功，存入SecurityContextHolder
        //注意这里使用了三个参数构造器，表示当前已经认证成功
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}