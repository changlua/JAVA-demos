package demo6;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolExecutorTest
 * @Author ChangLu
 * @Date 2021/4/1 11:34
 * @Description ThreadPoolExecutor七大参数介绍(包含三种策略)
 */
//四种不同策略(针对于连接数一旦大于max线程+阻塞队列容量情况)
//①new ThreadPoolExecutor.AbortPolicy()：即抛出异常RejectedExecutionException，不执行超出边界的任务
//②new ThreadPoolExecutor.CallerRunsPolicy()：呼叫main线程执行任务
//③new ThreadPoolExecutor.DiscardOldestPolicy()：丢弃最旧的未处理请求，并且重试任务请求，除非执行器突然被关闭该任务才会被丢弃
//④new ThreadPoolExecutor.DiscardPolicy()：直接丢弃拒绝的任务
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,//核心线程2个
                5,//本案例中池中最多有5个线程。推荐使用Runtime.getRuntime().availableProcessors()来动态获取可用于jvm的线程数
                2,//保持连接2秒
                TimeUnit.SECONDS,//2秒连接
                new LinkedBlockingDeque<>(3),//链表阻塞队列，容量为3
                Executors.defaultThreadFactory(),//默认的线程工厂
                new ThreadPoolExecutor.DiscardPolicy());//分别来测试4种不同的拒绝策略

        //测试线程
        try {
            for (int i = 0; i < 9; i++) {
                int temp = i;
                poolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 执行任务"+temp);
                });//执行线程
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();//关闭线程池
        }
    }

    @Test
    public void test01(){
        System.out.println();
    }
}