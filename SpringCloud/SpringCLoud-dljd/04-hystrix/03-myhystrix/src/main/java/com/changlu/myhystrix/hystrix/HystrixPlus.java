package com.changlu.myhystrix.hystrix;

import com.changlu.myhystrix.hystrix.model.HystrixStatus;
import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: changlu
 * @Date: 10:04 AM
 */
@Data
public class HystrixPlus {

    //时间窗口
    private static final Integer WINDOW_TIME = 20;
    //失败次数
    private static final Integer MAX_FAIL_COUNT = 3;

    //定义一个状态
    private HystrixStatus status = HystrixStatus.CLOSE;

    //错误次数计数器
    private AtomicInteger currentFailCount = new AtomicInteger(0);

    //定义一个线程池
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4,
            8,
30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    //锁
    private Object lock = new Object();

    {
        //提交定期清零报错次数
        poolExecutor.execute(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //根据当前的状态来判断是否要进行清理
                if (this.status.equals(HystrixStatus.CLOSE)) {
                    this.currentFailCount.set(0);
                }else {
                    // 半开或者开 不需要去记录次数 这个线程可以不工作
                    // 学过生产者 消费者模型  wait notifyAll  condition singleAll await   它们只能随机唤醒某一个线程
                    // lock锁 源码  CLH 队列 放线程 A B C D E  park unpark  可以 唤醒指定的某一个线程
//                    LockSupport.park();
//                    LockSupport.unpark();
                    try {
                        //进行阻塞，防止大量占据cpu
                        this.lock.wait();
                        System.out.println("开始进行失败次数清零操作");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //增加错误次数，若是错误此时达到瓶颈，那么就需要将当前状态转为open状态并提交定时任务来进行修改为half open状态，并且清零
    public void addFailCount() {
        int i = currentFailCount.incrementAndGet();
        if (i >= MAX_FAIL_COUNT) {
            //将当前熔断器状态设置开启状态
            this.status = HystrixStatus.OPEN;
            poolExecutor.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.status != HystrixStatus.CLOSE) {
                    //设置半开状态并且计数清零
                    this.status = HystrixStatus.HALF_OPEN;
                    this.currentFailCount.set(0);
                }
            });
        }
    }

}
