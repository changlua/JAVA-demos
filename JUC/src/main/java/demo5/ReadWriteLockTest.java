package demo5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest
 * @Author ChangLu
 * @Date 2021/3/30 11:23
 * @Description 读写锁ReadWriteLock测试
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        WRdata2 wRdata = new WRdata2();
        for (int i = 1; i <= 3; i++) {
            //闭包获取外部变量需要设置为final，jdk1.8默认会+上final
            int temp = i;
            new Thread(()->{
                for (int j=(temp-1)*10;j<=(temp-1)*10+10;j++){
                    //进行写操作
                    wRdata.write(j);
                }
            }).start();
        }

        new Thread(()->{
            for (int i = 1; i <= 30; i++) {
                wRdata.read(i);
            }
        }).start();

    }
}

//使用读写锁
class WRdata2{
    private volatile Map<String,String> map = new HashMap<>();
    //创建一个读写锁实例(默认是非公平锁)
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //分别获取读锁、写锁
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    //写操作
    public void write(int i){
        writeLock.lock();//上锁(写锁)
        try {
            System.out.println(Thread.currentThread().getName()+"执行写入操作，写入"+i);
            map.put(String.valueOf(i),Thread.currentThread().getName()+"执行"+i);
            System.out.println(Thread.currentThread().getName()+"写入"+i+"的操作完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();//释放锁(写锁)
        }
    }

    //读操作
    public void read(int i){
        readLock.lock();//上锁(写锁)
        try {
            String o = map.get(String.valueOf(i));
            System.out.println(Thread.currentThread().getName()+"读取到key="+i+"为"+o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();//释放锁(读锁)
        }
    }

}


//不加锁操作
class WRdata{
    private volatile Map<String,String> map = new HashMap<>();

    public void write(int i){
        System.out.println(Thread.currentThread().getName()+"执行写入操作");
        map.put(String.valueOf(i),Thread.currentThread().getName()+"执行"+i);
        System.out.println(Thread.currentThread().getName()+"写入操作完成");
    }

    public void read(int i){
        String o = map.get(String.valueOf(i));
        System.out.println(Thread.currentThread().getName()+"读取key="+i+"为"+o);
    }

}
