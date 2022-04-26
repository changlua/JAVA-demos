package com.changlu.ReentrantLock;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName DiyLock
 * @Author ChangLu
 * @Date 4/21/2022 8:42 AM
 * @Description 手写ReentrantLock：支持可重入，公平锁、非公平锁
 */
class LockTest2{

    private static DiyLock2 diyLock = new DiyLock2();
    private static volatile int num = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                diyLock.lock();
                diyLock.lock();
                num++;
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("Thread:" + Thread.currentThread().getName() + " => num:" + num);
                diyLock.unlock();
                diyLock.unlock();
            }, "Thread" + i).start();
        }
    }
}

public class DiyLock2 implements Lock {

    //设置一个阻塞队列
    private LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();

    //可重入，将AtomiceReference替换为Thread
    private volatile Thread owner = null;

    //新增属性：记录抢锁的数量
    private AtomicInteger count = new AtomicInteger();

    @Override
    public void lock() {
        if (!tryLock()) {
            //枪锁失败，添加到队列中
            Thread current = Thread.currentThread();
            //线程进入到等待队列
//            queue.add(current);
            //用于查看添加到队列的顺序，一定是要强一致的
            synchronized (DiyLock2.class) {
                queue.add(current);
                System.out.println("Thread:" + current.getName() + "已经添加到队列");//用于查看添加的顺序
            }
            //循环
            for(;;){
                Thread head = queue.peek();//获取到队首的线程
                if (current == head) {
                    //1、队首的线程来尝试抢10次进入阻塞
//                    for(int num=0;num < 10;num++){
//                        //若是当前线程在线程的头部，那么就再次尝试去抢
//                        if (!tryLock()) {
//                            if (num == 9) {
//                                //枪锁失败，就进入到阻塞中
//                                LockSupport.park();
//                            }
//                        }else {
//                            //枪锁成功，线程出队列
//                            queue.poll();
//                            return;
//                        }
//                    }
                    //2、队首线程尝试抢依次进入阻塞（只判断一次）
                    if (!tryLock()) {
                        //枪锁失败，就进入到阻塞中
                        LockSupport.park();
                    }else {
                        //枪锁成功，线程出队列
                        queue.poll();
                        return;
                    }
                }else {
                    //不在队头，直接给你挂起
                    LockSupport.park();
                }
            }
        }
    }

    /**
     * 在抢锁的过程中进行可重入的判断，依据的就是抢占锁的次数
     * @return true：上锁成功；false：上锁失败。
     */
    @Override
    public boolean tryLock() {
        Thread current = Thread.currentThread();
        //抢锁的次数
        int counts = count.get();
        //若是>0说明当前有线程已经抢到了
        if (counts > 0) {
            //如果锁是自己本身
            if (current == owner) {
                //重入次数+1
                count.set(counts + 1);
                return true;
            }else{
                return false;
            }
        }else {
            //次数为0，意味着没有线程抢到锁，使用CAS来抢锁
            if (count.compareAndSet(counts, counts + 1)) {
                //若是抢到锁
                owner = current;
                return true;
            }else {
                //抢锁失败
                return false;
            }
        }
    }

    /**
     * 主要做一个线程的释放操作
     */
    @Override
    public void unlock() {
        if (tryUnlock()) {
            //(1)公平
            //若是成功解锁，那么就唤醒头部线程队列
            Thread head = queue.peek();
            if (head != null) {
                LockSupport.unpark(head);
            }
            //(2)非公平
//            for (Object o : queue.toArray()) {
//                Thread thread = (Thread) o;
//                LockSupport.unpark(thread);
//            }
        }
    }

    /**
     * 进行锁的次数以及当前锁控制
     * @return
     */
    public boolean tryUnlock(){
        Thread current = Thread.currentThread();
        //判断是否是当前线程
        if (owner != current) {
            //若不是当前线程，直接抛出异常
            throw new IllegalMonitorStateException();
        }else {
            final int counts = count.get();
            int next = counts - 1;
            //若是为0表示当前要进行解锁了
            if (next == 0) {
                owner = null;
                count.set(next);
                return true;
            }else {
                //若next不为0，意味者还有重入的锁没有解，修改count即可，owner不变
                count.set(next);
                return false;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
