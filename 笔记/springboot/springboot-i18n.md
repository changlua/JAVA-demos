[toc]



# 思路

思路：通过spring工厂中获取到MessageSource,接着拿你提前定义好的code值，例如not.null，去messagesource里获取，即可获取到。



# 搭建过程

1、配置i18n

```yaml
server:
  port: 8789

spring:
  # 资源信息，注意是messages
  messages:
    # 国家或资源文件路径
    basename: i18n/messages
    # 配置为true表示如果在message.properties中找不到key也不会抛出异常
    use-code-as-default-message: true
```

2、建立i18n资源

![image-20220329095330820](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/3/202203290953849.png)  

```properties
# message.properties
not.null=不能为空

# message_en.properties
not.null=must not null
```

3、编写Utils来快速获取到MessageSource的对象

```java
@Component
public final class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware
{
    /** Spring应用上下文环境 */
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        SpringUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SpringUtils.applicationContext = applicationContext;
    }
    
   /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException
    {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }
}
```

```java
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
```

4、最后在controller中获取获取对应的code码即可

```java
import com.changlu.springbooti18n.utils.MessageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/testI18n")
    public String test() {
        //调用MessageUtils方法来进行获取
        String message = MessageUtils.message("not null");
        return message;
    }
}
```



# 测试

在默认的控制器中编写如下代码：

```java
@GetMapping("/testI18n")
public String test() {
    String message = MessageUtils.message("not.null");
    return message;
}
```

准备两个：中文不需要加`_xx`，其他文字内容添加`_xx`

![image-20220329094513442](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/3/202203290945471.png)  

```properties
not.null=不能为空

# en
not.null=must not null
```

当我们想要进行国际化请求时，那么只需要在每个请求头上加上`Accept-Language` 即可。

![image-20220329094717828](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/3/202203290947861.png)  

几个情况如下：当前项目只配有中文和英文

```
1、Accept-Language不加上去，直接发请求：默认就是会去中文里匹配
2、Accept-Language  zh或者当前项目中没有的：也会去中文中匹配
3、Accept-language  en：就会去匹配英文i18n中的内容
```

还有一种情况：就是在代码层面，去调用MessageUtils.message("not.null");，若是其中在i18n文件中没有not.null，就会直接返回`not.null`





---

整理者：长路   时间：2021.3.29