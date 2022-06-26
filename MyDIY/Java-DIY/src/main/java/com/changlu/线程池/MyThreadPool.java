package com.changlu.线程池;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 自定义线程池
 * @Author: changlu
 * @Date: 1:21 PM
 */
public class MyThreadPool {

    //默认线程池中线程的数量
    private static final int WORK_NUM = 5;

    //默认处理任务数量
    private static final int TASK_NUM = 100;

    //存放任务
    private final BlockingQueue<Runnable> taskQueue;

    //保存线程的集合
    private final Set<WorkThread> workThreads;

    private int workNumber;//线程数量

    private int taskNumber;//任务数量

    public MyThreadPool(){
        this(WORK_NUM , TASK_NUM);
    }

    public MyThreadPool(int workNumber , int taskNumber) {
        if (taskNumber<=0){
            taskNumber = TASK_NUM;
        }
        if (workNumber<=0){
            workNumber = WORK_NUM;
        }
        //创建一个数组阻塞队列
        this.taskQueue = new ArrayBlockingQueue<Runnable>(taskNumber);
        this.workNumber = workNumber;
        this.taskNumber = taskNumber;

        workThreads = new HashSet<WorkThread>();

        //工作线程处理好了，启动一定数量的线程数，从队列中获取任务处理
        for (int i = 0; i < workNumber; i++) {
            WorkThread workThread = new WorkThread("mythreadpool_" + i);
            workThread.start();
            workThreads.add(workThread);
        }
    }

    /**
     * 线程池执行任务方法：本质就是向阻塞队列中添加任务
     * @param task
     */
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //销毁线程池
    public void destroy(){
        System.out.println("ready close pool");
        for (WorkThread workThread : workThreads) {
            workThread.stopWorker();
            workThread = null;//help gc
        }
        //清楚所有元素
        workThreads.clear();;
    }


    //工作线程实现
    private class WorkThread extends Thread {
        public WorkThread(String name) {
            super();
            setName(name);
        }

        @Override
        public void run() {
            //检测是否被打断
            while(!interrupted()) {
                try {
                    Runnable runnable = taskQueue.take();//若是该线程执行了interrupt()这里就会抛出异常。（其他情况包含： notify（））
                    if (runnable != null) {
                        System.out.println(getName()+" ready execute:"+runnable.toString());
                        runnable.run();
                    }
                    runnable = null;//help GC
                } catch (InterruptedException e) {
                    interrupt();//很关键，在检测出异常时，还应该进行打断，这样才能够让run()方法停止
                    e.printStackTrace();
                }
            }
        }

        public void stopWorker() {
            //打断
            interrupt();
        }
    }

}
