package demo7;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CompletableFutureTest
 * @Author ChangLu
 * @Date 2021/4/3 16:54
 * @Description CompletableFuture(未来完成)：无返回值与有返回值示例
 */
public class CompletableFutureTest {

    //无返回值示例：使用runAsync()静态方法
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        System.out.println("---1111---");
        //1、调用runAsync()获取CompletableFuture实例，默认返回值为Void，即为空
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行....");
        });
        System.out.println("---2222---");
        //2、get()：调用时才执行其中任务，并获取到运行结果(可能出现阻塞)
        future.get();//执行任务即可，无需返回值(该案例为null)
        System.out.println("---3333---");
    }

    //有返回值的异步回调CompletableFuture.supplyAsync()
    @Test
    public void test02() throws ExecutionException, InterruptedException {
        System.out.println("---1111---");
        //1、使用supplyAsync()方法可以有返回值，可自由设置范围
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行任务.....");
            int num = 10/0;//模拟异常
            return 200;//操作正常，返回200
        });
        System.out.println("---2222---");
        //2、给执行的任务未来添加一系列的情况处理，如完成时操作，出现异常情况(可带返回值)
        //①、whenComplete——当完成任务时的操作
        Integer resultCode = future.whenComplete((u, v) -> {
            System.out.println(u);//u：执行完成任务的返回值
            System.out.println(v);//v：若无异常返回null，若有异常返回异常全限定类名+错误描述
        }).exceptionally((e) -> {//②、exceptionally()：抛出异常时的操作,应当有返回值
            e.printStackTrace();
            return 404;//表示执行有异常，返回错误码
        }).get();//③、get()——执行任务，获取到最终返回值(若案例若是无异常返回200，有异常返回400)
        System.out.println("获取返回值："+resultCode);
        System.out.println("---3333---");


    }
}