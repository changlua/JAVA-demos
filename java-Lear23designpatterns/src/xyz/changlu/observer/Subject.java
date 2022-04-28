package xyz.changlu.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Subject
 * @Author ChangLu
 * @Date 2021/3/19 19:17
 * @Description TODO
 */
//抽象主题
public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    //添加观察者到集合中
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    //移除指定观察者
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public abstract void notifyAllObserver();
}
