package demo5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ArrayBlockingQueueTest
 * @Author ChangLu
 * @Date 2021/3/30 16:21
 * @Description ArrayBlockingQueue:数组阻塞队列 4套(增、删)API测试
 */
public class ArrayBlockingQueueTest {

    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) throws InterruptedException {
        //test04();

        //测试阻塞的程序
//        new Thread(()->{
//            try {
//                test03();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                queue.add("F");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    //抛出异常
    public static void test01(){
        //创建空间为3的阻塞队列
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));//添加队列成功返回——true
        //System.out.println(queue.add("d"));//添加队列满情况抛异常——IllegalStateException
        System.out.println("-------------------");

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());//移除队列成功返回移除——元素
        //System.out.println(queue.remove());//队列为空情况抛出异常——NoSuchElementException
        System.out.println(queue.element());//获取队首元素，若无队首抛出异常——NoSuchElementException
    }

    //不抛出异常
    public static void test02(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //插入队列
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));//插入成功过的返回——true
        //System.out.println(queue.offer("d"));//队列满无法插入时无异常，返回——false
        System.out.println("-------------------");
        //移除队列
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());//移除成功返回的是元素
        //System.out.println(queue.poll());//队列空无法移除无异常，返回——null
        System.out.println(queue.peek());//获取队首元素，若无队首返回——null
    }

    //阻塞等待
    public static void test03() throws InterruptedException {
        //ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //插入队列
        queue.put("a");
        queue.put("b");
        queue.put("c");//插入队列成功无返回值
        //queue.put("d");//队列满无法插入时会进入阻塞
        System.out.println("-------------------");
        //移除队列
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());//移除队列成功有返回值为——元素
        System.out.println(queue.take());//队列元素为空无法移除时会进入阻塞(直到插入元素之后停止)
    }

    //超时等待
    public static void test04() throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //插入队列
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));//插入队列成功的话返回true
        System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));//队列已满情况时阻塞等2秒后若没有元素插入结束返回——false
        System.out.println("-------------------");
        //移除队列
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(2, TimeUnit.SECONDS));//队列为空时阻塞等待2秒，若还没有移除的元素返回——null
    }
}
