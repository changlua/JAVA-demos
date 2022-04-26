package com.changlu.springsecuritydemo03split.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName KatpchaNotMatchException
 * @Author ChangLu
 * @Date 3/24/2022 1:18 PM
 * @Description 验证码不匹配异常
 */
public class KaptchaNotMatchException extends AuthenticationException {

    public KaptchaNotMatchException(String msg, Throwable cause){
        super(msg, cause);
    }

    public KaptchaNotMatchException(String msg){
        super(msg);
    }

}