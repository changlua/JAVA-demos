package xyz.changlu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName MsgRunnable
 * @Author ChangLu
 * @Date 2021/5/15 17:58
 * @Description TODO
 */
public class MsgRunnable implements Runnable{

    Socket socket;

    public MsgRunnable(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            StringBuilder str = new StringBuilder();
            //获取输入流，进行读取
            is = socket.getInputStream();
            byte[] data = new byte[1024];
            int len;
            while((len = is.read(data))!=-1){
                str.append(new String(data,0,len));
                socket.shutdownInput();
            }
            //输出输入流
            //System.out.println(str);
            //如果接收请求为空直接结束（POST MAN工具每次发送除get请求外都会发两次请求，第一次为空，第二次为真实请求），所以这里进行省略
            if("".equals(str) || str.length() == 0)
                return;
            //重要：封装请求对象
            Request request = Request.buildRequest(str);

            //获取到请求路径，如/index.html
            //String path = str.toString().split("\r\n")[0].split(" ")[1].substring(1);
            //重要：封装响应对象
            Response response = new Response();
            response.setData(HttpUtils.getPage(request.getUrl().substring(1)));
            //进行响应
            os = socket.getOutputStream();
            response.write(os);//传入输出流即可
            //os.write(HttpUtils.getResponse(HttpUtils.getPage(path)));//os.write(getResponse(getPage("index.html")));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}