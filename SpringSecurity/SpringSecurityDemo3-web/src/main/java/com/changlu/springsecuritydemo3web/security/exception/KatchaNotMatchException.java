package com.changlu.springsecuritydemo3web.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName KatchaNotMatchException
 * @Author ChangLu
 * @Date 3/23/2022 10:29 PM
 * @Description 自定义验证码异常
 */
public class KatchaNotMatchException extends AuthenticationException {

    public KatchaNotMatchException(String msg, Throwable cause){
        super(msg,cause);
    }

    public KatchaNotMatchException(String msg){
        super(msg);
    }

}