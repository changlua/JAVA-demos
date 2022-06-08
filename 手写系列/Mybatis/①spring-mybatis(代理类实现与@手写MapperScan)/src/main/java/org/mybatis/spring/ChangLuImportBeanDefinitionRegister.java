package org.mybatis.spring;

import com.changlu.mapper.OrderMapper;
import com.changlu.mapper.UserMapper;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName ChangLuImportBeanDefinitionRegister
 * @Author ChangLu
 * @Date 2021/8/22 23:14
 * @Description TODO
 */
//ImportBeanDefinitionRegistrar该类专门就用来进行注册BeanDefinition
public class ChangLuImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata
     * @param registry AnnotationConfigApplicationContext实际上是实现了该接口的，所以这里也能够进行注册BeanDefinition
     * @param importBeanNameGenerator
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        //拿到引用该注册器的注解，获取指定的包路径
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ChangLuScan.class.getName());
        String path  = (String) annotationAttributes.get("value");

//        String path = "com.changlu.mapper";
        //借助使用Spring的扫描器来扫描指定路径
        ChangLuMapperScanner mapperScanner = new ChangLuMapperScanner(registry);
        mapperScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;//修改为true
            }
        });
        //由于ChangLuMapperScanner重写了doScan方法，在执行scan()方法内就会去执行我们写的doScan方法接着就会将对应定义的BeanDefinition进行自动注册
        int scan = mapperScanner.scan(path);//默认扫描的是带有@Component等一些实体类，对于接口的话就不会扫描到
        System.out.println(scan);

        //ChangLuMapperScanner重写的doScan方法直接替换了下面内容，仅仅只进行修改BeanDefinition结构并让Spring自己来进行注册BeanDefinition
//        List<Class> list = new ArrayList<>(2);
//        list.add(UserMapper.class);
//        list.add(OrderMapper.class);
//
//        int i = 1;
//        for (Class aClass : list) {
//            //编程式
//            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//            beanDefinition.setBeanClass(MapperFactoryBean.class);//设置工厂类
//            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(aClass);//手动构造器设置值，指定代理接口类
//            //借助参数registry来进行注册BeanDefinition。(与AnnotationConfigApplicationContext注册效果一致)
//            registry.registerBeanDefinition("xx" + i++, beanDefinition);
//        }
    }
}