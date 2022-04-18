package xyz.changlu.strategy.demo2;

/**
 * @ClassName NormalZombie
 * @Author ChangLu
 * @Date 2021/3/20 23:31
 * @Description TODO
 */
//普通僵尸
public class NormalZombie extends Zombie {

    public NormalZombie() {
        //普通僵尸默认攻击为咬，移动方式为朝一个方向移动
        super(new BiteAttach(), new DirectionMove());
    }

    public NormalZombie(Attachable attachable, Moveable moveable) {
        super(attachable, moveable);
    }

    @Override
    void describe() {
        System.out.println("我是一个普通僵尸");
    }

    @Override
    void appearance() {
        System.out.println("一顶绿帽子");
    }

    @Override
    void movie() {
        moveable.movie();//指定指定行为接口方法
    }

    @Override
    void attach() {
        attachable.attach();
    }
}
