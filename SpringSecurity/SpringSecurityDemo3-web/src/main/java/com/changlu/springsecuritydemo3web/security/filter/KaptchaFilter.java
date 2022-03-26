package com.changlu.springsecuritydemo3web.security.filter;

import com.changlu.springsecuritydemo3web.security.exception.KatchaNotMatchException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName KaptchaFilter
 * @Author ChangLu
 * @Date 3/23/2022 10:06 PM
 * @Description 验证码过滤器
 */
public class KaptchaFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SECURITY_FORM_KAPTCHA_KEY = "kaptcha";

    private String kaptchaParameter = SECURITY_FORM_KAPTCHA_KEY;

    public String getKaptchaParameter() {
        return kaptchaParameter;
    }

    public void setKaptchaParameter(String kaptchaParameter) {
        this.kaptchaParameter = kaptchaParameter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //1、从请求中获取验证码
        String verifyCode = request.getParameter(this.getKaptchaParameter());
        //2、从session中获取验证码
        String sessionVerifyCode = (String) request.getSession().getAttribute("kaptcha");
        if (!ObjectUtils.isEmpty(sessionVerifyCode) && !ObjectUtils.isEmpty(verifyCode)  //两个验证码非空
            && verifyCode.equalsIgnoreCase(sessionVerifyCode)){   //两个验证码相等
            return super.attemptAuthentication(request, response);
        }
        //其他情况抛出异常
        throw new KatchaNotMatchException("验证码不匹配");
    }
}