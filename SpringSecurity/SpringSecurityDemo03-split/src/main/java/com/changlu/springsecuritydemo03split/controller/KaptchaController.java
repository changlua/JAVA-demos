package com.changlu.springsecuritydemo03split.controller;

import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName KaptchaController
 * @Author ChangLu
 * @Date 3/24/2022 1:13 PM
 * @Description 验证码控制器
 */
@RestController
public class KaptchaController {
    private final Producer producer;

    @Autowired
    public KaptchaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/getVerifyCode")
    public String getVerifyCode(HttpSession session) throws IOException {
        //1、生成验证码
        String text = producer.createText();
        //2、存储到session中（或存储到redis里）
        session.setAttribute("kaptcha", text);
        //3、写入到内存中
        BufferedImage bi = producer.createImage(text);
        FastByteArrayOutputStream fbaos = new FastByteArrayOutputStream();
        ImageIO.write(bi, "png", fbaos);
        //4、生成Base64编码返回
        return Base64.encodeBase64String(fbaos.toByteArray());
    }
}