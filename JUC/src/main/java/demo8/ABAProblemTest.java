package demo8;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ABAProblemTest
 * @Author ChangLu
 * @Date 2021/4/5 22:35
 * @Description 模拟测试AtomicInteger(其他原子类)可能会出现的ABA问题
 */
public class ABAProblemTest {

    //使用AtomicInteger来模拟出ABA问题
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);

        //线程A模拟ABA问题情况
        new Thread(()->{
            //在线程B睡眠过程中执行交换操作
            atomicInteger.compareAndSet(10,22);
            atomicInteger.compareAndSet(22,10);
            //ABA问题就出现在这里，当上面换完之后，引用依旧不会变，再执行下面41行时不会检测到其已经进行了更新操作
        },"A").start();

        //线程B进行主要核心替换操作
        new Thread(()->{
            do{
                try {
                    //模拟Unsafe中的获取值
                    //报出异常：java.lang.SecurityException: Unsafe，无法通过自定义类来获取到该实例
                    Integer i = Unsafe.getUnsafe().getIntVolatile(atomicInteger,getUnsafeValueOffset());
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }while (!atomicInteger.compareAndSet(10,66));//重要！经过上面的交换操作本句代码依旧会正常执行！替换为66
            System.out.println(atomicInteger);
        },"B").start();

    }

    //通过反射来获取到Unsafe类中的valueOffset值
    public static long getUnsafeValueOffset() throws IllegalAccessException, NoSuchFieldException {
        Field field = Unsafe.class.getDeclaredField("valueOffset");
        field.setAccessible(true);
        return (long) field.get(Unsafe.getUnsafe());
    }
}