package com.changlu.ReentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName demo
 * @Author ChangLu
 * @Date 4/20/2022 11:34 PM
 * @Description ReentrantLock示例
 */
public class demo {

    private static ReentrantLock lock = new ReentrantLock();
    private static volatile int num;
    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                for (int j = 0; j < 10; j++) {
                    num++;
                }
                lock.unlock();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(num);
    }
}
