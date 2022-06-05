package xyz.changlu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @ClassName HttpUtils
 * @Author ChangLu
 * @Date 2021/5/15 17:57
 * @Description Http工具类
 */
public class HttpUtils {

    public static Logger logger = Logger.getLogger("xyz.changlu");


    /**
     * 获取相应的字节数组
     * @param data 数据内容(html....)
     * @return 字节数组
     */
    public static byte[] getResponse(String data){
        //其中Content-type:text/html;charset=utf-8解决中文乱码问题
        //注意点：Content-length的长度应该是字节数组的长度(UTF-8下的中文，一个中文三个字节或四个字节，英文一个字节)
        return ("HTTP/1.1 200 ok\r\nContent-type:text/html;charset=utf-8\r\nContent-length="+ data.getBytes().length +"\r\n\r\n" +data).getBytes();
    }

    /**
     * 将classpath路径的指定html读取成字符串返回
     * @param path 指定资源 *.html
     * @return 字符串
     * @throws IOException
     */
    public static String getPage(String path) throws IOException {
        //通过StringBuilder来进行拼接文件读到的字节数组
        StringBuilder strBuilder = new StringBuilder();
        //方式一：读取jar包下指定资源路径下的指定path资源
        //InputStream is = Server.class.getClassLoader().getResourceAsStream("index.html");
        //InputStream is = Server.class.getClassLoader().getResourceAsStream(path);
        //方式二：读取jar包外的pages目录下的静态资源(打jar包时使用)
        String jarPath = Server.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        jarPath = jarPath.substring(0,jarPath.lastIndexOf("/"))+"/pages/";//重新组装路径
        //System.out.println(jarPath);
        String newPath = jarPath + path;//组成完整的资源路径
        //判断是否存在该文件资源
        boolean exists = new File(newPath).exists();
        if(!exists){
            newPath = jarPath+"404.html";
        }
        InputStream is = new FileInputStream(newPath);//通过文件读取路径

        //若是文件资源为空跳转404页面
//        if(is == null){
//            is = Server.class.getClassLoader().getResourceAsStream("404.html");
//        }
        //读取文件资源
        byte[] data = new byte[1024];
        int len;
        while((len = is.read(data))!=-1){
            strBuilder.append(new String(data,0,len));
        }
        //模拟模板引擎解析
        if("login.html".equals(path)){
            //模拟数据库中查询的用户名
            String username = "changlu";
            //将读取文件的流中字符串进行模板替换
            String str = strBuilder.toString().replace("{{username}}}", "changlu");
            return str;
        }
        //关闭流
        is.close();
        return strBuilder.toString();
    }
}