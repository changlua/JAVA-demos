package com.changlu.springsecuritydemo03split.security.filter;

import com.changlu.springsecuritydemo03split.security.exception.KaptchaNotMatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName LoginFilter
 * @Author ChangLu
 * @Date 3/23/2022 4:45 PM
 * @Description 自定义前后端分离认证 Filter
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

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
        System.out.println("===========================");
        //1、判断是否是Post请求
        if (!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2、判断是否为JSON格式数据请求（即"application/json;charset=UTF-8"）
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            //3、从json数据中获取用户名与密码来进行认证：{"uname":"xxx","passwd":"xxx"}
            try {
                Map<String,String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username = userInfo.get(getUsernameParameter());//通过这种方式取getUsernameParameter，好处是可以在外部自行定义名称
                String password = userInfo.get(getPasswordParameter());
                String verifyCode = userInfo.get(getKaptchaParameter());
                System.out.println("用户名：" + username + ",密码：" + password + ",验证码：" + verifyCode);
                String sessionVerifyCode = (String) request.getSession().getAttribute("kaptcha");//从session中获取到验证码
                //4、校验验证码是否正确
                if (!ObjectUtils.isEmpty(verifyCode) && !ObjectUtils.isEmpty(sessionVerifyCode) &&
                    verifyCode.equalsIgnoreCase(sessionVerifyCode)
                ){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
                    setDetails(request, authenticationToken);//配置request身份信息(仿写父类调用的方法)
                    return this.getAuthenticationManager().authenticate(authenticationToken);//真正进行认证
                }
                throw new KaptchaNotMatchException("验证码有误");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return super.attemptAuthentication(request,response);//尝试调用父类的认证方法
        //抛出异常
        throw new RuntimeException("请求类型有误,请确保类型为application/json;charset=UTF-8！");
    }
}