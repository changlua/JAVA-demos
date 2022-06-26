package com.changlu.线程池;

/**
 * @Description: 自定义线程池任务
 * @Author: changlu
 * @Date: 1:33 PM
 */
public class Test {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(10, 10);
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "exec ...");
            }
        };
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);

        //终止线程
        myThreadPool.destroy();
    }

//    public static void main(String[] args) {
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                while (!Thread.interrupted()) {
//
//                }
//            }
//        });
//        thread.start();
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();
//    }
}
