package org.mybatis.spring;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName MapperFactoryBean
 * @Author ChangLu
 * @Date 2021/8/22 22:46
 * @Description TODO
 */

public class MapperFactoryBean implements FactoryBean{

    //执行要进行代理的mapperclass接口
    private Class mapperClass;

    private SqlSession sqlSession;

    public MapperFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

//    @Autowired  //省略注解，在
    public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
        this.sqlSession = sqlSessionFactory.openSession();
    }

//    @Override
//    public Object getObject() throws Exception {
//        //将代理对象作为bean对象返回，并且对应的beanName为userMapper
//        return Proxy.newProxyInstance(MapperFactoryBean.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return Arrays.asList(new User(),new User());
//            }
//        });
//    }


    @Override
    public Object getObject() throws Exception {
        return sqlSession.getMapper(mapperClass);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }
}