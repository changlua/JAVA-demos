package com.changlu.springsecuritydemo05jwt.security.expression;

import com.changlu.springsecuritydemo05jwt.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @ClassName SGExpressionRoot
 * @Author ChangLu
 * @Date 3/25/2022 9:44 PM
 * @Description 自定义权限
 */
@Component("ex")
public class SGExpressionRoot {

    public boolean hasAuthority(String authority){
        //1、获取当前用户的所有权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //2、判断当前用户是否具备改权限进行访问
        return loginUser.getPermissions().contains(authority);
    }

}