package xyz.changlu;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName server
 * @Author ChangLu
 * @Date 2021/5/15 13:37
 * @Description 服务器
 */
public class Server {

    public static void main(String[] args)throws Exception{
        //服务器
        ServerSocket serverSocket = new ServerSocket(8899);
        HttpUtils.logger.info("Server is running,open in port 8899!");
        //创建一个线程池
        ExecutorService service = Executors.newFixedThreadPool(30);
        //服务器可循环监听
        while(true){
            //监听
            Socket socket = serverSocket.accept();
            HttpUtils.logger.info("收到ip地址为："+socket.getRemoteSocketAddress()+" 的请求！");
            //提交给线程池任务
            service.submit(new MsgRunnable(socket));
        }
    }



}