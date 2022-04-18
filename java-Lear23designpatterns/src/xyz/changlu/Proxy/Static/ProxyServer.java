package xyz.changlu.Proxy.Static;

import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyServer
 * @Author ChangLu
 * @Date 2021/3/17 23:07
 * @Description TODO
 */
//代理服务器
public class ProxyServer implements NetWork{

    private NetWork server;

    //传入被代理人(同样实现了NetWork接口)
    public ProxyServer(NetWork server){
        this.server = server;
    }

    @Override
    public void browser() {
        //代理服务器来进行检查操作
        check();

        //被代理类开始真正联网操作
        server.browser();;
    }

    public void check(){
        System.out.println("联网之前被代理类正在进行检查操作...");
    }
}
