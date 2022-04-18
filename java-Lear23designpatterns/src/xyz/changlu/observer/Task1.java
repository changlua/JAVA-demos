package xyz.changlu.observer;

/**
 * @ClassName Task1
 * @Author ChangLu
 * @Date 2021/3/19 19:21
 * @Description TODO
 */
//具体观察者1
public class Task1 implements Observer {
    @Override
    public void response() {
        System.out.println("Task1收到通知，正在执行任务2.....");
    }
}
