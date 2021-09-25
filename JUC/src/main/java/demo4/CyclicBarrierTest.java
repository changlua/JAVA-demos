package demo4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CyclicBarrierTest
 * @Author ChangLu
 * @Date 2021/3/29 17:51
 * @Description CyclicBarrier(循环屏障)使用
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //创建一个循环屏障，设置为7
        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠，终极进化！");
        });

        for (int i = 1; i < 8; i++) {
            //i属于局部变量存在于栈，在下面Lambda表达式中若想要使用该变量应该将其存放于一个final变量中
            //闭包，jdk1.8可以不写final，jvm会默认加上去的
            int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"集齐了第"+temp+"龙珠");
                try {
                    //等待有7个线程执行这个等待方法时一起通过
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"结束");
            }).start();
        }



    }
}
