package xyz.changlu.servlet;

/**
 * @ClassName Servlet
 * @Author ChangLu
 * @Date 2021/5/17 20:54
 * @Description Servlet接口
 */
public interface Servlet {

    void init();

    void service(Request request, Response response);

    void destory();

}
