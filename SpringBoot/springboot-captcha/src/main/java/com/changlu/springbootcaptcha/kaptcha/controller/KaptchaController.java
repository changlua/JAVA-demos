package com.changlu.springbootcaptcha.kaptcha.controller;

import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KaptchaController
 * @Author ChangLu
 * @Date 4/12/2022 7:13 PM
 * @Description Kaptcha控制器
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 1、获取算数表达式验证码
     * @return
     * @throws Exception
     */
    @GetMapping("/captcha1")
    public Map<String,String> mathKaptcha() throws Exception{
        //1、生成验证码
        String code = captchaProducerMath.createText();
        String capStr = code.substring(0, code.lastIndexOf("@"));
        code = code.substring(code.lastIndexOf("@") + 1);
        //2、获取Base64编码
        BufferedImage bi = captchaProducerMath.createImage(capStr);
        FastByteArrayOutputStream fbaos = new FastByteArrayOutputStream();
        ImageIO.write(bi, "png", fbaos);
        String kaptchaBase64 = Base64.encodeBase64String(fbaos.toByteArray());
        //3、返回结果集
        Map<String,String> result = new HashMap<>(2);
        result.put("code", code);
        result.put("base64", "data:image/png;base64," + kaptchaBase64);
        return result;
    }


    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    /**
     * 2、获取字符验证码
     * @return
     * @throws Exception
     */
    @GetMapping("/captcha2")
    public Map<String,String> charKaptcha() throws Exception{
        //1、生成验证码（这里就简单些）
        String capStr = null, code = null;
        capStr = code = captchaProducer.createText();
        //2、获取Base64编码
        BufferedImage bi = captchaProducer.createImage(capStr);
        FastByteArrayOutputStream fbaos = new FastByteArrayOutputStream();
        ImageIO.write(bi, "png", fbaos);
        String kaptchaBase64 = Base64.encodeBase64String(fbaos.toByteArray());
        //3、返回结果集
        Map<String,String> result = new HashMap<>(2);
        result.put("code", code);
        result.put("base64", "data:image/png;base64," + kaptchaBase64);
        return result;
    }

}
