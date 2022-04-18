package xyz.changlu.strategy.demo2;

/**
 * @ClassName FlagZombie
 * @Author ChangLu
 * @Date 2021/3/20 23:39
 * @Description TODO
 */
//旗手僵尸
public class FlagZombie extends Zombie{

    public FlagZombie() {
        //普通僵尸默认攻击为咬，移动方式为一瘸一拐移动
        super(new BiteAttach(), new StepToStepMove());
    }

    public FlagZombie(Attachable attachable, Moveable moveable) {
        super(attachable, moveable);
    }

    @Override
    void describe() {
        System.out.println("我是一个旗帜僵尸");
    }

    @Override
    void appearance() {
        System.out.println("一顶绿帽子");
    }

    @Override
    void movie() {
        moveable.movie();
    }

    @Override
    void attach() {
        attachable.attach();
    }
}
