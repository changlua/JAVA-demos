package demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockDemo
 * @Author ChangLu
 * @Date 2021/3/25 20:08
 * @Description 使用Lock锁来实现生产者消费者(同样实现效果)
 */
public class LockDemo1 {
    public static void main(String[] args) {
        Data3 data = new Data3();
        //多个生产线程、消费线程测试
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

class Data3{
    private int num;
    private Lock lock = new ReentrantLock();//①可重入锁实例化
    Condition condition = lock.newCondition();//通过指定锁来获取Condition实例(ConditionObject实例)

    //生产
    public void add() throws InterruptedException {
        lock.lock();//上锁
        try {
            //核心业务
            while (num != 0) {
                condition.await();//阻塞
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            condition.signalAll();//唤醒所有等待线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }


    //消费
    public void minus() throws InterruptedException {
        lock.lock();//上锁
        try {
            while(num == 0){
                condition.await();//阻塞等待
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            condition.signalAll();//唤醒所有线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }

    }
}