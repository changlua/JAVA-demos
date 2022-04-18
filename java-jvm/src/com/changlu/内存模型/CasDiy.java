package com.changlu.内存模型;

import sun.misc.Unsafe;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @ClassName CasDiy
 * @Author ChangLu
 * @Date 4/20/2022 9:41 AM
 * @Description 手写cas
 */
public class CasDiy {
    public static void main(String[] args) throws InterruptedException {
        //测试有没有获取到
//        System.out.println(DataContainer.unsafe);
        final DataContainer dataContainer = new DataContainer();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                dataContainer.increase();
            }
            countDownLatch.countDown();
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                dataContainer.decrease();
            }
            countDownLatch.countDown();
        });
        thread1.start();
        thread2.start();
        //等待计时结束来查看一下data的值
        countDownLatch.await();
        System.out.println(dataContainer.getData());//正常的话就是0
    }
}

class DataContainer{
    private volatile int data;//多个线程同时改变的一个数据
    static Unsafe unsafe;
    static long DATA_OFFSET;

    public int getData() {
        return data;
    }

    static {
        //通过反射来获取到Unsafe
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //获取DATA_OFFSET在DataContainer中的一个偏移量
        try {
            DATA_OFFSET = unsafe.objectFieldOffset(DataContainer.class.getDeclaredField("data"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自增
     */
    public void increase(){
        int oldValue;
        while (true) {
            oldValue = data;
            //修改的操作让cas来给我们进行，若是成功执行则返回，失败就继续自旋
            if (unsafe.compareAndSwapInt(this, DATA_OFFSET, data, data + 1)){
                System.out.println(data);
                return;
            }
            System.out.println(Thread.currentThread().getName() + "正在重试...");
        }
    }

    /**
     * 自增
     */
    public void decrease(){
        int oldValue;
        while (true) {
            oldValue = data;
            //修改的操作让cas来给我们进行，若是成功执行则返回，失败就继续自旋
            if (unsafe.compareAndSwapInt(this, DATA_OFFSET, data, data - 1)){
                System.out.println(data);
                return;
            }
            System.out.println(Thread.currentThread().getName() + "正在重试...");
        }
    }

}
