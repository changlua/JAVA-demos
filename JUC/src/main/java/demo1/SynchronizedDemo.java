package demo1;

/**
 * @ClassName SynchronizedDemo
 * @Author ChangLu
 * @Date 2021/3/25 16:38
 * @Description Synchronized的卖票
 */
public class SynchronizedDemo {
    public static void main(String[] args)  {
        Ticket ticket = new Ticket();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"A").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"B").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"C").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sellTicket();},"D").start();
    }
}

class Ticket{
    private int num = 50;

    //通过使用同步方法来进行加锁
    public synchronized void sellTicket(){
        if(num > 0){
            System.out.print(Thread.currentThread().getName());
            System.out.println("卖出第"+num--+"张票，还剩余"+num+"张票");
        }
    }
}