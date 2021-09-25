package demo3;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName CopyOnWriteList
 * @Author ChangLu
 * @Date 2021/3/26 14:10
 * @Description CopyOnWriteList：测试读写分离
 */
public class CopyOnWriteListTest {
    public static void main(String[] args) {
        //问题描述：List<Object> list = new ArrayList<>(); 线程不安全的
        //解决1：Collections.synchronizedList(new ArrayList<>()) 线程安全的(实际就是包裹了一层synchronized)
        //解决2：new Vector<>() 线程安全的(jdk1.0就出现了，其所有方法都是同步的)
        //解决3：使用CopyOnWriteArrayList(读写分离)
        List<Object> list = new CopyOnWriteArrayList<>();
        //多线程下进行add操作
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }
            //System.out.println(list);
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }
            //System.out.println(list);
        },"B").start();

    }
}
