package demo8;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerTest
 * @Author ChangLu
 * @Date 2021/4/5 16:18
 * @Description CAS(保证线程安全的一种较为高效方式)来解决变量自增问题
 */
public class AtomicIntegerTest {
    //案例一：通过i++自增复合操作来引出CAS，本案例是具有线程安全问题的
    private volatile static int num = 0;

    @Test
    public void test01() throws InterruptedException {
        Thread[] threads = new Thread[10];
        //10个线程分别进行100次自增，预期结果为num=100000
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    num++;
                }
            });
            threads[i].start();
        }
        //让10个线程优先执行
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(num);//获取最终的num值
    }




    //案例二：改进案例一，解决num复合操作带来的线程安全问题，通过使用Atomic原子类
    private static AtomicInteger num1 = new AtomicInteger();//改进一：使用原子类AtomicInteger

    @Test
    public void test02() throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    num1.getAndIncrement();//表示+1  改进二：将num++更改使用getAndIncrement()
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(num1.get());
    }

    //案例3：测试AtomicInteger中的CAS方法compareAndSet()：实际上就是直接调用的Unsafe的本地方法compareAndSwapInt()，下面是解释
    //             若是当前值为期望值，那么会将当前值更改为更新值，并返回true；反之当前值不为期望值，更改失败返回false
    private static AtomicInteger num2 = new AtomicInteger(1011);
    @Test
    public void test03(){
        //期望值为1011，更新值为1012
        System.out.println(num2.compareAndSet(1011, 1012));
        System.out.println(num2);
        //期望值为1011，更新值为1013
        System.out.println(num2.compareAndSet(1011, 1013));
        System.out.println(num2);
    }
}