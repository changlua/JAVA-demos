package xyz.changlu.strategy.demo2;

/**
 * @ClassName Zombie
 * @Author ChangLu
 * @Date 2021/3/20 23:26
 * @Description TODO
 */
//僵尸抽象类
public abstract class Zombie {
    Attachable attachable;
    Moveable moveable;
    //对应的抽象方法
    abstract void describe();
    abstract void appearance();
    abstract void movie();
    abstract void attach();

    //初始化时具备指定能力
    public Zombie(Attachable attachable, Moveable moveable) {
        this.attachable = attachable;
        this.moveable = moveable;
    }

    public Attachable getAttachable() {
        return attachable;
    }

    public void setAttachable(Attachable attachable) {
        this.attachable = attachable;
    }

    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }
}
