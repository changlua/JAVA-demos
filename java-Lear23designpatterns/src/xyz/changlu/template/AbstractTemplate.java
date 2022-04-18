package xyz.changlu.template;

/**
 * @ClassName AbstractTemplate
 * @Author ChangLu
 * @Date 2021/3/19 21:38
 * @Description TODO
 */
//抽象模板类
public abstract class AbstractTemplate {

    //一整套数据库查询方法(连接数据库、执行查询操作、关闭连接操作)
    public void templateMethod(){
        System.out.println("初始化操作");
        System.out.println("连接数据库操作");
        abstractMethod1();
        abstractMethod2();
        System.out.println("关闭连接操作");
    }

    public abstract void abstractMethod1();

    public abstract void abstractMethod2();
}
