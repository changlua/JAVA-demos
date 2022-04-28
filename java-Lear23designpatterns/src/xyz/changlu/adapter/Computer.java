package xyz.changlu.adapter;

/**
 * @ClassName Computer
 * @Author ChangLu
 * @Date 2021/3/17 21:15
 * @Description TODO
 */
public class Computer {

    //进行上网
    public void net(NetToUSB adapter){
        //调用适配器的方法
        adapter.handleRequest();
    }
}
