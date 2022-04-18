package xyz.changlu.singleton;

/**
 * @ClassName Hungry
 * @Author ChangLu
 * @Date 2021/3/16 21:23
 * @Description TODO
 */
//方式一：饿汉式
public class Hungry {

    //构造器私有
    private Hungry(){
    }

    //直接通过静态属性来创建单例
    private static final Hungry HUNGRY = new Hungry();

    //获取单例
    public static Hungry getInstance(){
        return HUNGRY;
    }
}

class Test{
    //测试单例
    public static void main(String[] args) {
        Hungry instance = Hungry.getInstance();
        Hungry instance1 = Hungry.getInstance();
        System.out.println(instance == instance1);

    }
}
