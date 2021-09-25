package demo4;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Author ChangLu
 * @Date 2021/3/29 16:39
 * @Description CountDownLatch：同步工具类(完成指定数量任务之后一定要做的事)
 */
public class CountDownLatchTest1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行了");
                latch.countDown();//计数器-1
            },String.valueOf(i)).start();
        }

        latch.await();//主线程进入阻塞状态等待锁存器计数到0，才继续往下执行
        System.out.println("close door！");
    }
}
