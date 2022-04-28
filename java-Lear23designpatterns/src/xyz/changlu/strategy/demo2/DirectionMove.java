package xyz.changlu.strategy.demo2;

/**
 * @ClassName DirecationMove
 * @Author ChangLu
 * @Date 2021/3/20 23:35
 * @Description TODO
 */
//朝一个方向移动
public class DirectionMove implements Moveable {
    @Override
    public void movie() {
        System.out.println("朝着一个方法前进！");
    }
}
