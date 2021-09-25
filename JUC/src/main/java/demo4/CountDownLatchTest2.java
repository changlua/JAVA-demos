package demo4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatchTest2
 * @Author ChangLu
 * @Date 2021/3/29 17:16
 * @Description CountDownLatch使用2：秒杀场景(倒计时开抢)
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    latch.await();//进行等待操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"开始执行!");
            }).start();
        }

        //模拟倒计时2秒
        for (int i = 3; i >=1; i--) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }
        System.out.println("开抢！！！");
        //计数-1指挥线程开始行动
        latch.countDown();
    }
}
