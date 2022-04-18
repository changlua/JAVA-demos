package xyz.changlu.adapter;

/**
 * @ClassName Adatper
 * @Author ChangLu
 * @Date 2021/3/17 21:18
 * @Description TODO
 */
//类适配器实现
public class Adapter extends Reticle implements NetToUSB{

    @Override
    public void handleRequest() {
        super.request();
    }
}
