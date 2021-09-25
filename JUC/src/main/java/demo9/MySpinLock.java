package demo9;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SpinLockTest
 * @Author ChangLu
 * @Date 2021/4/6 10:20
 * @Description 自定义自旋锁(借助cas)
 */
public class MySpinLock {

    //默认其中值为null
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    //上锁
    public void myLock() {
        System.out.println(Thread.currentThread().getName()+"开始准备上锁");
        //只有当其中值为null时才能够进行更改，相当于进行上锁
        //若是一直执行不了cas操作，那么就会一直处于循环阻塞
        while (!threadAtomicReference.compareAndSet(null,Thread.currentThread())){
            //用于提示阻塞效果！！！进行延时，若是过多调用compareAndSet程序会终止
            System.out.println(Thread.currentThread().getName()+"阻塞等待锁中....");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"已经上锁");
    }

    //解锁
    public void myUnlock(){
        //将原子引用中的线程设置为null，表示为解锁
        threadAtomicReference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName()+"已解锁");
    }

    //测试一下自定义自旋锁通过使用cas来进行上锁、解锁
    public static void main(String[] args) throws InterruptedException {
        //自定义锁
        MySpinLock lock = new MySpinLock();

        new Thread(()->{
            lock.myLock();//进行上锁
            try {
                System.out.println(Thread.currentThread().getName()+"执行任务....");
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();//进行解锁
            }
        },"A").start();

        //确保线程A先执行
        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lock.myLock();//进行上锁
            try {
                System.out.println(Thread.currentThread().getName()+"执行任务....");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();//进行解锁
            }
        },"B").start();
    }

}