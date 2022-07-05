package 面试题;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:50 PM
 */
class Clerk {
    //产品数量
    private volatile int product;

    //生产产品
    public void produceProduct() {
        if (product < 100) {
           synchronized (this) {
               if (product < 100) {
                   product++;
                   System.out.println(Thread.currentThread().getName() + "正在开始生产产品：" + product);
                   this.notify();
               }else {
                   //若是数量>100就不进行生产了，进入阻塞
                   try {
                       this.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
        }
    }

    //消费产品
    public void consumerProduct() {
        if (product > 0) {
            synchronized (this) {
                if (product > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在消费产品：" + product);
                    product--;
                    //进行唤醒
                    this.notify();
                }else if (product == 0) {
                    //进入阻塞
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}

class Producer implements Runnable {

    private Clerk clerk;

    public Producer (Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            clerk.produceProduct();
        }
    }
}

class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer (Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            clerk.consumerProduct();
        }
    }
}

public class ProducerAndConsumerDemo {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        Thread thread1 = new Thread(producer, "线程1");
        Thread thread2 = new Thread(consumer, "线程2");
        thread1.start();
        thread2.start();
    }
}
