package xyz.changlu.observer;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/19 19:23
 * @Description TODO
 */
public class Customer {
    public static void main(String[] args) {
        //创建两个任务
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        //创建主题角色,并添加两个观察者(及任务)
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(task1);
        subject.addObserver(task2);

        //进行更新通知
        subject.notifyAllObserver();
    }
}
