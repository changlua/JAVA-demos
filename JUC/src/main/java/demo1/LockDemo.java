package demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockDemo
 * @Author ChangLu
 * @Date 2021/3/25 16:47
 * @Description Lock锁 可重入锁的使用(卖票)
 */
public class LockDemo {
    public static void main(String[] args)  {
        Ticket2 ticket = new Ticket2();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"A").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"B").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"C").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"D").start();
    }
}

class Ticket2{
    private int num = 50;
    Lock lock = new ReentrantLock();//①可重入锁实例化(默认是非公平锁)

    //使用lock锁
    public void sellTicket(){
        lock.lock();//②上锁
        try {
            if(num > 0){
                System.out.print(Thread.currentThread().getName());
                System.out.println("卖出第"+num--+"张票，还剩余"+num+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//③释放锁
        }

    }
}