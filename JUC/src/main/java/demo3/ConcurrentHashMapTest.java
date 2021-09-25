package demo3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ConcurrentHashMapTest
 * @Author ChangLu
 * @Date 2021/3/28 22:21
 * @Description ConcurrentHashMap解决Map的安全问题
 */
public class ConcurrentHashMapTest {
    //Map<String,String> map = new HashMap<>()：使用普通的集合在多线程下会有线程安全问题(ConcurrentModificationException)
    //1、Collections.synchronizedMap(new HashMap<>())：线程安全
    //2、new ConcurrentHashMap<>()：线程安全(并发包中引出的)
    private static Map<String,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //创建10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                //每个线程存储10个
                for (int j = 0; j < 20; j++) {
                    map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                    System.out.println(map);
                }
            }).start();
        }
    }
}
