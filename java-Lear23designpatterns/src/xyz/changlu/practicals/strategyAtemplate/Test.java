package xyz.changlu.practicals.strategyAtemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Author: changlu
 * @Date: 6:12 PM
 */
@Configuration
@ComponentScan("xyz.changlu")
@EnableAspectJAutoProxy
class AutoConfig{
}

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoConfig.class);
        UploadStrategyContext uploadStrategyContext = context.getBean(UploadStrategyContext.class);
        Test test = new Test();
        //测试一下（默认走的是local）
        uploadStrategyContext.executeUploadStrategy("path", null);
        uploadStrategyContext.executeDeleteStrategy("path");

        //自定义模式（oss）
        uploadStrategyContext.executeUploadStrategy(UploadModeEnum.OSS.getMode(), "path", null);
        uploadStrategyContext.executeDeleteStrategy(UploadModeEnum.OSS.getMode(), "path");
    }

}
