package xyz.changlu.singleton;

/**
 * @ClassName Lazy
 * @Author ChangLu
 * @Date 2021/3/16 21:26
 * @Description TODO
 */
//第二种：懒汉式(双重校验锁+volatile，保证原子性)，需要解决线程安全问题
public class Lazy {

    //设置私有构造器
    private Lazy(){
        System.out.println(Thread.currentThread().getName());//用于多线程测试
    }

    //设置属性为原子性
    private volatile static Lazy lazy;

    //获取单例
    public static Lazy getInstance(){
        if(lazy == null){
            synchronized (Lazy.class){
                if(lazy == null){
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }
}

class Test1{
    public static void main(String[] args) {
        //多线程来测试单例
        for (int i = 0; i < 10; i++) {
            new Thread(()->{Lazy.getInstance();}).start();
        }
    }
}
