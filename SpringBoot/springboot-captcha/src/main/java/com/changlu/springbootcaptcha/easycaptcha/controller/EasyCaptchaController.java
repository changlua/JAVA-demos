package com.changlu.springbootcaptcha.easycaptcha.controller;

import com.changlu.springbootcaptcha.easycaptcha.enums.CodeTypeEnum;
import com.changlu.springbootcaptcha.easycaptcha.service.EasyCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName CaptchaController
 * @Author ChangLu
 * @Date 4/12/2022 5:44 PM
 * @Description Easy-captcha控制器
 */
@Controller
@RestController
public class EasyCaptchaController {

    @Autowired
    private EasyCaptchaService easyCaptchaService;

    //1、算术类型
    @GetMapping("/captcha1")
    public Map getGifcCaptcha1(){
        return easyCaptchaService.getCaptchaValueAndBase64(null);
    }

    //2、Gif
    @GetMapping("/captcha2")
    public Map getGifcCaptcha2(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.GIF);
    }

    //3、png类型
    @GetMapping("/captcha3")
    public Map getGifcCaptcha3(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.SPEC);
    }

    //4、chinese文字类型
    @GetMapping("/captcha4")
    public Map getGifcCaptcha4(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.CHINESE);
    }

    //4、chinese Gif类型
    @GetMapping("/captcha5")
    public Map getGifcCaptcha5(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.CHINESE_GIF);
    }

}
