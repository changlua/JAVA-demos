package xyz.changlu.observer;

/**
 * @ClassName Task2
 * @Author ChangLu
 * @Date 2021/3/19 19:22
 * @Description TODO
 */
//具体观察者2号
public class Task2 implements Observer {
    @Override
    public void response() {
        System.out.println("Task2收到通知，正在执行任务2.....");
    }
}
