package demo2;

/**
 * @ClassName SynchronizedDemo
 * @Author ChangLu
 * @Date 2021/3/25 17:43
 * @Description 生产者与消费者问题(使用synchronized)
 */
public class SynchronizedDemo2 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        //两个线程为生产者、两个线程为消费者
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

class Data2{
    private int num;

    //+1操作
    public synchronized void add() throws InterruptedException {
        //使用while确保在被唤醒时不会直接执行下面代码
        while (num != 0){
            wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        notifyAll();
    }

    //-1操作
    public synchronized void minus() throws InterruptedException {
        while (num == 0){
            wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        notifyAll();
    }
}
