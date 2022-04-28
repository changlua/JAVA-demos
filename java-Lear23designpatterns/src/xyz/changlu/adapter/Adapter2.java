package xyz.changlu.adapter;

/**
 * @ClassName Adapter2
 * @Author ChangLu
 * @Date 2021/3/17 21:22
 * @Description TODO
 */
//对象适配器实现
public class Adapter2 implements NetToUSB{

    private Reticle reticle;

    //传入指定的适配者
    public Adapter2(Reticle reticle){
        this.reticle = reticle;
    }

    @Override
    public void handleRequest() {
        reticle.request();
    }
}
