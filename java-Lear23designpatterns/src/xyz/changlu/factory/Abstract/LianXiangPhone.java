package xyz.changlu.factory.Abstract;

/**
 * @ClassName LianXiangPhone
 * @Author ChangLu
 * @Date 2021/3/16 0:03
 * @Description TODO
 */
public class LianXiangPhone implements Iphone {
    @Override
    public void start() {
        System.out.println("联想手机正在启动...");
    }

    @Override
    public void music() {
        System.out.println("联想手机播放音乐中...");
    }

    @Override
    public void ring() {
        System.out.println("联想手机开启闹铃...");
    }

    @Override
    public void stop() {
        System.out.println("联想手机关机中...");
    }
}
