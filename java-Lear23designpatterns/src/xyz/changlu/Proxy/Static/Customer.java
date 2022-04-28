package xyz.changlu.Proxy.Static;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/17 23:10
 * @Description TODO
 */
public class Customer {
    public static void main(String[] args) {
        //通过传入被代理实例让代理类进行联网操作
        new ProxyServer(new Server()).browser();
    }
}
