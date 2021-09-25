package demo5;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronousQueueTest
 * @Author ChangLu
 * @Date 2021/3/31 21:48
 * @Description SynchronousQueue小案例
 *
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        //创建一个同步队列
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        //该线程用于入队操作
        new Thread(()->{
            try {
                queue.put("A");
                System.out.println(Thread.currentThread().getName()+"put A");
                queue.put("B");
                System.out.println(Thread.currentThread().getName()+"put B");
                queue.put("C");
                System.out.println(Thread.currentThread().getName()+"put C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //该线程用于取队列中的元素
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                queue.take();
                TimeUnit.SECONDS.sleep(2);
                queue.take();
                TimeUnit.SECONDS.sleep(2);
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}