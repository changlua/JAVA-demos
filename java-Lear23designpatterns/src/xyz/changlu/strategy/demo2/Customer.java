package xyz.changlu.strategy.demo2;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/20 23:41
 * @Description TODO
 */
//测试类
public class Customer {
    public static void main(String[] args) {
        //普通僵尸、旗帜僵尸的特征属性都来进行打印
        System.out.println("--------默认情况--------");
        NormalZombie normalZombie = new NormalZombie();
        printFeature(normalZombie);
        FlagZombie flagZombie = new FlagZombie();
        printFeature(flagZombie);

        //切换僵尸不同的特征，看僵尸的对应特征是否按照策略模式使用
        //普通僵尸的攻击方式改为头撞方式，旗帜僵尸的攻击方式改为头撞方式，行走方式改为朝一个方向移动
        System.out.println("--------修改指定的策略后--------");
        normalZombie.setAttachable(new HeadAttach());//更改普通僵尸
        flagZombie.setAttachable(new HeadAttach());//更改旗帜僵尸
        flagZombie.setMoveable(new DirectionMove());
        printFeature(normalZombie);
        printFeature(flagZombie);

    }

    //执行某个僵尸的所有动作
    public static void printFeature(Zombie zombie){
        zombie.describe();
        zombie.appearance();
        zombie.attach();
        zombie.movie();
        System.out.println();
    }
}
