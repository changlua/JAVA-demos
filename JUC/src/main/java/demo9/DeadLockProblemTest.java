package demo9;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DeadLockProblemTest
 * @Author ChangLu
 * @Date 2021/4/6 11:41
 * @Description 死锁案例，主要用于死锁排查测试
 */
public class DeadLockProblemTest implements Runnable {

    private String A;
    private String B;

    public DeadLockProblemTest(String a, String b) {
        A = a;
        B = b;
    }

    @Override
    public void run() {
        synchronized (A){
            System.out.println(Thread.currentThread().getName()+"获取到了锁："+A);

            //进行延时确保出现死锁问题
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (B){
                System.out.println(Thread.currentThread().getName()+"获取到了锁："+A);
            }
        }
    }

    public static void main(String[] args) {
        String A = "A";
        String B = "B";
        new Thread(new DeadLockProblemTest(A,B)).start();
        new Thread(new DeadLockProblemTest(B,A)).start();

    }

}