package xyz.changlu.factory.Abstract;

/**
 * @ClassName LianXiangPhone
 * @Author ChangLu
 * @Date 2021/3/16 0:03
 * @Description TODO
 */
public class HuaWeiPhone implements Iphone {
    @Override
    public void start() {
        System.out.println("华为手机启动中...");
    }

    @Override
    public void music() {
        System.out.println("华为手机播放音乐中...");
    }

    @Override
    public void ring() {
        System.out.println("华为手机开启闹铃中...");
    }

    @Override
    public void stop() {
        System.out.println("华为手机关机中...");
    }
}
