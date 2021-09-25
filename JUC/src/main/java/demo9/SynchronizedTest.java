package demo9;

/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 2021/4/6 9:33
 * @Description Synchronzied(可重入锁)测试
 */
//测试synchronized本质可重入锁
public class SynchronizedTest {
    public static void main(String[] args) {
        new SynchronizedTest().use1();
    }

    //锁为LockTest实例
    public synchronized void use1(){
        System.out.println("首次上锁使用use1()方法");
        use2();//调用了同步方法
    }

    //该方法锁依旧为LockTest实例
    public synchronized void use2(){
        System.out.println("二次上锁使用use2()方法");
    }
}
