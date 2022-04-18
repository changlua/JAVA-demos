package com.changlu.springbootcaptcha.easycaptcha.config;

import com.changlu.springbootcaptcha.easycaptcha.enums.CodeTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EasyCaptchaConfig
 * @Author ChangLu
 * @Date 4/12/2022 5:28 PM
 * @Description 验证码控制器
 */
@Data
@ConfigurationProperties(prefix = "easycaptcha")
@Configuration
public class EasyCaptchaConfig {

    /**
     * 验证码配置
     */
    private CodeTypeEnum codeType;
    /**
     * 验证码内容长度
     */
    private int length = 4;
    /**
     * 验证码宽度
     */
    private int width = 111;
    /**
     * 验证码高度
     */
    private int height = 36;
    /**
     * 验证码字体
     */
    private String fontName;
    /**
     * 字体大小
     */
    private int fontSize = 25;

}
