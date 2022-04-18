package xyz.changlu.observer;

/**
 * @ClassName ConcreteSubject
 * @Author ChangLu
 * @Date 2021/3/19 19:20
 * @Description TODO
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notifyAllObserver() {
        System.out.println("收到通知");
        //开始通知到所有的观察者
        for (Observer observer : observers) {
            observer.response();
        }
    }
}
