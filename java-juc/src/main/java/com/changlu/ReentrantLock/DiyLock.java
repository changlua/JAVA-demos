package com.changlu.ReentrantLock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName DiyLock
 * @Author ChangLu
 * @Date 4/21/2022 8:42 AM
 * @Description 手写ReentrantLock：不可重入锁实现（公平与非公平）
 */
class LockTest{

    private static DiyLock diyLock = new DiyLock();
    private static volatile int num = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                diyLock.lock();
                num++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("num:" + num);
                diyLock.unlock();
            }, "Thread" + i).start();
        }
    }
}

public class DiyLock implements Lock {

    //设置一个阻塞队列
    private LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();

    //原子类（若是我们要对某个时间端操作强一致，那么就可以使用AtomicStampedReference）
    private AtomicReference<Thread> cas = new AtomicReference<>();


    @Override
    public void lock() {
        //尝试上锁，上锁成功直接结束，上锁失败就会添加到队列中
        while (!cas.compareAndSet(null, Thread.currentThread())){
            queue.add(Thread.currentThread());//添加到队列中，若是队列满会直接抛出异常
            System.out.println("线程" + Thread.currentThread().getName() + "上锁失败，添加到队列中...");
            //本质调用cas的park方法来让当前线程进入阻塞（无期限暂停当前线程）
            LockSupport.park();
            System.out.println("线程" + Thread.currentThread().getName() + "阻塞结束");
            queue.remove(Thread.currentThread());//队列中移除该线程队列元素为空无法移除时会抛出异常
        }
        System.out.println("线程" + Thread.currentThread().getName() + "上锁成功");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (cas.compareAndSet(Thread.currentThread(), null)) {
            System.out.println("线程" + Thread.currentThread().getName() + "解锁成功");
            //1、唤醒所有队列中的线程，让他们去进行争抢（非公平）
//            for (Object o : queue.toArray()) {
//                Thread thread = (Thread) o;
//                System.out.println(thread.getName() + ":进行唤醒操作");
//                LockSupport.unpark(thread);//对该线程进行唤醒操作
//                System.out.println(thread.getName() + "唤醒成功");
//            }
            //2、公平的，按照队列方式取出来一个
            if (queue.size() > 0) {
                Thread thread = queue.remove();
                LockSupport.unpark(thread);
            }
        }else{
            //唤醒失败不做任何事情
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
