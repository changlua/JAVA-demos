package com.changlu.springsecuritydemo3web.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName KaptchaController
 * @Author ChangLu
 * @Date 3/23/2022 10:09 PM
 * @Description 验证码控制器
 */
@Controller
public class KaptchaController {

    @Autowired
    private Producer producer;

    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        //1、创建验证码
        String text = producer.createText();
        //2、在session中存储验证码(或者redis中)
        session.setAttribute("kaptcha", text);
        //3、生成图片
        BufferedImage bufferedImage = producer.createImage(text);
        //4、响应图片
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(bufferedImage,"jpg", os);//将生成的图片写入到流中。bufferedImage=>os
    }

}