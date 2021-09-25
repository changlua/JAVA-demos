package demo4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreTest
 * @Author ChangLu
 * @Date 2021/3/29 18:52
 * @Description Semaphore(信号量)简单使用
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//通过有参构造器创建指定数量的许可证
        //创建8个线程(相当于8辆车去占3个车位)
        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                try {
                    //首先需要发布
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了停车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "归还了停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //释放掉许可证
                semaphore.release();
            }).start();
        }
    }
}
