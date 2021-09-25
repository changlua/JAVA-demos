package demo6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName ExecutorsThreeMethodTest
 * @Author ChangLu
 * @Date 2021/3/31 22:43
 * @Description Executors三大线程池方法的使用
 */
public class ExecutorsThreeMethodTest {
    public static void main(String[] args) {
        ExecutorService es1 = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService es2 = Executors.newFixedThreadPool(5);//创建一个固定线程的线程池
        ExecutorService es3 = Executors.newCachedThreadPool();//创建一个可伸缩的线程池，可根据你的执行次数来分配

        //测试es1
        //testExecutors(es1);
        //测试es2
        //testExecutors(es2);
        //测试es2
        testExecutors(es3);

    }

    //传入一个通过Executors不同创建的方法所获取的ExecutorService
    public static void testExecutors(ExecutorService es){
        //执行20次方法来查看其中分别使用了多少个线程
        try {
            for (int i = 0; i < 20; i++) {
                es.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"执行！");
                });//线程池执行操作
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.shutdown();//开闭线程池
        }
    }

}