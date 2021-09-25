package demo2;

/**
 * @ClassName SynchronizedDemo1
 * @Author ChangLu
 * @Date 2021/3/25 17:57
 * @Description 使用synchronized模拟生产者消费者问题(该例子两个线程下无问题，超过两个线程出现安全问题
 * 原因描述：在多个生产线程、消费线程情况下生产、消费操作仅使用if判断会有线程安全问题。
 *          若仅有1个生产线程、消费线程则不会出现安全问题。
 */
public class SynchronizedDemo1 {
    public static void main(String[] args) {
        Data data = new Data();
        //一个线程为生产者、一个线程为消费者
        //仅有两个线程时不会出现问题，当出现三四个线程时会出现安全问题
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

class Data{
    private int num;

    //+1操作
    public synchronized void add() throws InterruptedException {
        //num≠0阻塞释放锁
        if (num != 0){
            wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        notifyAll();
    }

    //-1操作
    public synchronized void minus() throws InterruptedException {
        //num为0阻塞释放锁
        if (num == 0){
            wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        notifyAll();
    }
}