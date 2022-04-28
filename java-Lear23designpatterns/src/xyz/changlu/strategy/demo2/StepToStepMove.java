package xyz.changlu.strategy.demo2;

/**
 * @ClassName StepToStepMove
 * @Author ChangLu
 * @Date 2021/3/20 23:36
 * @Description TODO
 */
//一瘸一拐移动
public class StepToStepMove implements Moveable{
    @Override
    public void movie() {
        System.out.println("一撅一拐向前行进");
    }
}
