package com.changlu.springsecuritydemo05jwt.service.impl;

import com.changlu.springsecuritydemo05jwt.domain.LoginUser;
import com.changlu.springsecuritydemo05jwt.domain.ResponseResult;
import com.changlu.springsecuritydemo05jwt.domain.pojo.User;
import com.changlu.springsecuritydemo05jwt.mapper.UserMapper;
import com.changlu.springsecuritydemo05jwt.security.exception.ServiceException;
import com.changlu.springsecuritydemo05jwt.service.LoginService;
import com.changlu.springsecuritydemo05jwt.utils.JwtUtil;
import com.changlu.springsecuritydemo05jwt.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginServiceImpl
 * @Author ChangLu
 * @Date 3/25/2022 2:50 PM
 * @Description 登录业务处理器
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        //1、进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = null;
        try {
           authenticate = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){  //注意在这里可以捕捉到BadCredentialsException异常
            if (e instanceof BadCredentialsException){
                throw new ServiceException("密码错误");  //抛出密码错误异常
            }else{
                throw new ServiceException(e.getMessage());
            }
        }
        //2、若是认证没有通过，抛出异常
        if (ObjectUtils.isEmpty(authenticate)) {
            throw new RuntimeException("用户认证失败");
        }
        //3、认证通过，将用户信息取出存储到redis
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        redisCache.setCacheObject("login:" + userId, loginUser);
        //4、根据UserId来生成Token进行返回
        String token = JwtUtil.createJWT(userId);
        Map<String,String> result = new HashMap<>(1);
        result.put("token", token);
        return new ResponseResult(200, "登录成功", result);
    }

}
