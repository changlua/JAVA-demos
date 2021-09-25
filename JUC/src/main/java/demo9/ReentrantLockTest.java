package demo9;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockTest
 * @Author ChangLu
 * @Date 2021/4/6 9:40
 * @Description ReentrantLock(可重入锁)测试
 */
public class ReentrantLockTest {
    //创建一个可重入锁
    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new ReentrantLockTest().use1();
    }

    public void use1() {
        lock.lock();//上锁
        try {
            System.out.println("use1()方法使用");
            use2();//使用use2()方法
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }

    public void use2() {
        lock.lock();//上锁
        try {
            System.out.println("use2()方法使用");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }
}