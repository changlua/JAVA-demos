package demo3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName CopyOnWriteSetTest
 * @Author ChangLu
 * @Date 2021/3/28 21:50
 * @Description CopyOnWriteSet(读写分离Set并解决线程安全问题)
 */
public class CopyOnWriteSetTest {
    //private static Set<String> set = new HashSet<>();：多线程下有线程安全问题
    //1、Collections.synchronizedSet(new HashSet<>())：使用工具类来包装一下，线程安全
    //2、new CopyOnWriteArraySet()：使用并发包中的cow读写分离的set，线程安全的！
    private static Set<String> set = new CopyOnWriteArraySet();

    public static void main(String[] args) {
        //开辟20个线程
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                    set.add(UUID.randomUUID().toString());
                }
                System.out.println(set);
            }).start();
        }

    }
}
