package demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockDemo2
 * @Author ChangLu
 * @Date 2021/3/25 21:10
 * @Description 通过使用Condition来实现精准通知，唤醒线程
 */
public class LockDemo2 {
    public static void main(String[] args) {
        Work work = new Work();
        //三个线程进行执行
        new Thread(()->{for (int i = 0; i < 20; i++) work.printA();},"1").start();
        new Thread(()->{for (int i = 0; i < 20; i++) work.printB();},"2").start();
        new Thread(()->{for (int i = 0; i < 20; i++) work.printC();},"3").start();
    }
}

//多线程下执行顺序为A->B->C
class Work{
    private Lock lock = new ReentrantLock();//可重入锁
    //创建三个同步监视器
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int num = 1;


    public void printA(){
        lock.lock();//上锁
        try {
            while (num != 1){
                condition1.await();//进入阻塞
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAAAAA");
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }

    public void printB(){
        lock.lock();//上锁
        try {
            //核心业务
            while (num != 2){
                condition2.await();//进入阻塞
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBBBBB");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }

    public void printC(){
        lock.lock();//上锁
        try {
            while (num != 3){
                condition3.await();//进入阻塞
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCCCCC");
            num = 1;
            condition1.signal();//唤醒锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }


}
