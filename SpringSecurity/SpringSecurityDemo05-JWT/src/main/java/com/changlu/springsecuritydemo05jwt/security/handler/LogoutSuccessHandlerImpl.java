package com.changlu.springsecuritydemo05jwt.security.handler;

import com.alibaba.fastjson.JSON;
import com.changlu.springsecuritydemo05jwt.domain.LoginUser;
import com.changlu.springsecuritydemo05jwt.domain.ResponseResult;
import com.changlu.springsecuritydemo05jwt.utils.RedisCache;
import com.changlu.springsecuritydemo05jwt.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LogoutSuccessHandlerImpl
 * @Author ChangLu
 * @Date 3/25/2022 5:16 PM
 * @Description 注销成功处理器
 */

//方案二：自定义成功注销处理器
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private RedisCache redisCache;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //1、获取用户id
        if (!ObjectUtils.isEmpty(authentication)) {
            String userId = ((LoginUser) authentication.getPrincipal()).getUser().getId().toString();
            //2、根据用户id来删除redis存储的用户信息
            redisCache.deleteObject("login:" + userId);
        }
        ServletUtils.renderString(response, JSON.toJSONString(new ResponseResult(200, "注销成功！")));
    }
}