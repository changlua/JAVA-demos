package xyz.changlu.Proxy.Static;

/**
 * @ClassName Server
 * @Author ChangLu
 * @Date 2021/3/17 23:06
 * @Description TODO
 */
//真实服务器
public class Server implements NetWork{
    @Override
    public void browser() {
        System.out.println("真实的服务器访问网络...");
    }
}
