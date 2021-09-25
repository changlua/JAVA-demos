package org.mybatis.spring;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 继承Spring的扫描器
 * @ClassName ChangLuMapperScanner
 * @Author ChangLu
 * @Date 2021/8/22 23:21
 * @Description TODO
 */
public class ChangLuMapperScanner extends ClassPathBeanDefinitionScanner {
    public ChangLuMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 默认该方法是用于检测是否是一个合法的Bean，会过滤掉接口
     * 改写过后来去判断是否是一个接口，是的话返回true，否则返回false
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    /**
     * 对读取到的所有BeanDefinition进行替换(对应接口的代理类)
     * @param basePackages
     * @return
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        //走的还是Spring的扫描路径
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        //此时就拿到了其中的所有BeanDefinition（OrderMapper、UserMapper....）
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            //提前将对应的ClassName添加至构造器参数中(这里指的是MapperFactoryBean的构造器，仅仅是提前进行而已)
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            //进行狸猫换太子，将原本的接口类替换为代理类
            beanDefinition.setBeanClassName(MapperFactoryBean.class.getName());
            //相当于在MapperFactoryBean的setSqlSession()上设置@Autowired
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
        return beanDefinitionHolders;
    }
}