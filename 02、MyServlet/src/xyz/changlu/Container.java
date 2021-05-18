package xyz.changlu;

import xyz.changlu.controller.IndexServlet;
import xyz.changlu.servlet.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName Container
 * @Author ChangLu
 * @Date 2021/5/17 20:55
 * @Description 容器
 */
public class Container {

    //servlet容器(对应url)
    public static final Map<String, Servlet> SERVLETS = new HashMap<String, Servlet>(8){
        {
            put("/index",new IndexServlet());
        }
    };

    //用来读取配置文件
    public static final Properties PROPERTIES = new Properties();

    static{
        //加载当前项目中的配置文件
        try {
            Container.PROPERTIES.load(Catalina.class.getClassLoader().getResourceAsStream("web.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}