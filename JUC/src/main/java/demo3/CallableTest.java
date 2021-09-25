package demo3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableTest
 * @Author ChangLu
 * @Date 2021/3/28 23:25
 * @Description 测试使用Callable
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }

        //获取返回值
        String s = task.get();//会有阻塞
        System.out.println(s);

    }
}

class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("执行MyCallable()中的call()方法");
        return "调用成功！";
    }
}
