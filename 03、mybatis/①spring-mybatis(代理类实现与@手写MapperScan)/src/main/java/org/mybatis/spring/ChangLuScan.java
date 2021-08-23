package org.mybatis.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ChangLuScan
 * @Author ChangLu
 * @Date 2021/8/23 21:59
 * @Description TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ChangLuImportBeanDefinitionRegister.class)   //自动引入配置类
public @interface ChangLuScan {
    String value();
}
