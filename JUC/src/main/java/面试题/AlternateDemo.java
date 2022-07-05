package 面试题;

/**
 * @Description: 线程通信小例子(交替打印1-100)
 * @Author: changlu
 * @Date: 2:33 PM
 */
class MyRunnable implements Runnable {

    private volatile int i;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (i < 100) {
                    //唤醒操作
                    this.notify();
                    i++;
                    System.out.println(Thread.currentThread().getName() + ": " + i);

                    //进入等待
                    if (i < 100) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    break;
                }
            }
        }
    }
}

public class AlternateDemo {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        thread1.setName("线程1");
        Thread thread2 = new Thread(myRunnable);
        thread2.setName("线程2");
        thread1.start();
        thread2.start();

    }

}
