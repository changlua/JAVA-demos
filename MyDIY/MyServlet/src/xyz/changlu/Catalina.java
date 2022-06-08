package xyz.changlu;

import xyz.changlu.controller.NotFoundServlet;
import xyz.changlu.servlet.Request;
import xyz.changlu.servlet.Response;
import xyz.changlu.servlet.Servlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Catalina
 * @Author ChangLu
 * @Date 2021/5/17 20:47
 * @Description 自定义Catalina作为servlet容器
 */
public class Catalina {
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);
        System.out.println("myserver is running! Open port in 8899");

        while(true){
            //监听到socket
            Socket socket = serverSocket.accept();
            //获取socket的输入流与输出流
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //读取数据
            StringBuilder str = new StringBuilder();
            byte[] data = new byte[1024];
            int len;
            while((len = is.read(data))!=-1){
                str.append(new String(data,0,len));
                socket.shutdownInput();
            }

            //构造出来一个request请求
            Request request = Request.buildRequest(str);
            //构造出response请求
            Response response = new Response();
            response.setOs(os);//存储输出流


            //通过url请求来进行选择用来进行初始化Servlet
            Servlet servlet = null;
            if("/favicon.ico".equals(request.getUrl())){
                continue;
            }
            servlet = Container.SERVLETS.get(request.getUrl());
            //若是在集合中没有找到到对应的请求地址
            if(servlet == null){
                //查找配置文件中请求地址对应的值
                String className = Container.PROPERTIES.getProperty(request.getUrl());//从配置文件中读取到对应的servlet权限丁类名
                if(className != null){
                    servlet = (Servlet) Class.forName(className).newInstance();//通过反射来获取到指定的servlet
                    Container.SERVLETS.put(request.getUrl(),servlet);//添加到map集合中去
                }else{
                    //配置404页面
                    servlet = new NotFoundServlet();
                }
            }

            //执行业务
            servlet.service(request,response);


            //关闭连接
            socket.close();
            is.close();
        }

        //serverSocket.close();

    }
}