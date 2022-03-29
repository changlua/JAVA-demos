package com.changlu.springbooti18n.utils;

import com.changlu.springbooti18n.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @ClassName MessageUtils
 * @Author ChangLu
 * @Date 3/29/2022 9:22 AM
 * @Description 获取i18n资源文件
 */
public class MessageUtils {

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键，也就是
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object ...args){
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code,args, LocaleContextHolder.getLocale());
    }

}
