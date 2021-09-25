package demo7;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @ClassName ForkJoinTest
 * @Author ChangLu
 * @Date 2021/4/1 17:02
 * @Description ForkJoin使用(工作窃取特性 ， 并行处理)
 */
public class ForkJoinTest {

    //继承递归任务抽象类
    static class MyForkJoinTask extends RecursiveTask<Long> {

        private Long start;
        private Long end;
        private Long temp = 10000L;

        public MyForkJoinTask(Long start, Long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            //最终达到我们预期范围来去真正执行的操作
            if (end - start < temp) {
                Long sum = 0L;
                for (Long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                Long middle = (start + end) / 2;
                MyForkJoinTask task1 = new MyForkJoinTask(start, middle);
                task1.fork();//拆解任务
                MyForkJoinTask task2 = new MyForkJoinTask(middle + 1, end);
                task2.fork();//拆解任务
                return task1.join() + task2.join();//将拆解的两个任务相加获取返回值
            }
        }
    }

    //测试使用ForkJoin来并行归并处理任务
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        //ForkJoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //需要提交一个递归任务(自定义的递归任务)
        ForkJoinTask<Long> submit = forkJoinPool.submit(new MyForkJoinTask(1L, 1000000000L));
        Long sum = submit.get();//获取运算结果
        long end = System.currentTimeMillis();
        System.out.println("时间为：" + (end - start) / 1000.0 + "秒");//时间为：5.438秒
        System.out.println("sum=" + sum);//sum=500000000500000000
    }

    //①普通计算
    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 1L; i <= 1000000000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("时间为：" + (end - start) / 1000.0 + "秒");//时间为：0.767秒
        System.out.println("sum=" + sum);//sum=500000000500000000
    }

    //②流计算（LongStream）
    @Test
    public void test02(){
        long start = System.currentTimeMillis();
        //①获取操作(rangeClosed())：获取LongPipeline实现类。
        //②中间操作(parallel())：设置该流为并行流
        //③结果操作(reduce())：归约，合并操作。属性：起始值  函数式(相隔两个操作数执行的操作)
        long sum = LongStream.rangeClosed(0L, 1000000000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("时间为：" + (end - start) / 1000.0 + "秒");//时间为：：0.368秒
        System.out.println("sum=" + sum);//sum=500000000500000000
    }


}