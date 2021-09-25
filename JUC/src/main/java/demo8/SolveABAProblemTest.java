package demo8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName SolveABAProblemTest
 * @Author ChangLu
 * @Date 2021/4/5 23:14
 * @Description 通过使用AtomicStampedReference(版本号机制)，利用其标记机制来放置出现ABA问题！
 */
public class SolveABAProblemTest {

    //设置泛型为Integer，参数设置初始值为1，标记为1
    private static AtomicStampedReference<Integer> sr = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {
        //线程A模拟ABA问题
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);//睡一秒保证线程B先获取到标记
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("标记为"+sr.getStamp());
            //模拟ABA问题，从1->11  11->1
            boolean b1 = sr.compareAndSet(1, 11, sr.getStamp(), sr.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"=>标记为"+sr.getStamp()+",当前值为"+sr.getReference()+","+b1);
            boolean b2 = sr.compareAndSet(11, 1, sr.getStamp(), sr.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"=>标记为"+sr.getStamp()+",当前值为"+sr.getReference()+","+b1);
        },"A").start();

        //线程B，主要测试AtomicStampedReference通过标记是否有效解决ABA问题
        new Thread(() -> {
            //获取当前标记
            int stamp = sr.getStamp();
            //睡眠一会让线程A模拟ABA问题
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //看一下是否能够预防ABA问题出现！注意这里的stamp使用的是之前获取到的标记号
            //若是true则表示更改成功，false表示更改失败
            System.out.println(sr.compareAndSet(1, 66, stamp, stamp + 1));
            System.out.println(Thread.currentThread().getName()+"=>标记为"+sr.getStamp()+",值为"+sr.getReference());
        }, "B").start();

    }
}