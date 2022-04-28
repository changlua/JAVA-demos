package xyz.changlu.factory.Abstract;

/**
 * @ClassName LianXiangRouter
 * @Author ChangLu
 * @Date 2021/3/16 0:05
 * @Description TODO
 */
public class LianXiangRouter implements Router{
    @Override
    public void start() {
        System.out.println("联想路由器正在启动...");
    }

    @Override
    public void stop() {
        System.out.println("联想路由器正在关机...");
    }

    @Override
    public void work() {
        System.out.println("联想路由器开始工作...");
    }
}
