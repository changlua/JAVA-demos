package xyz.changlu.servlet;

/**
 * @ClassName HttpServlet
 * @Author ChangLu
 * @Date 2021/5/17 21:35
 * @Description TODO
 */
public class HttpServlet extends GenericServlet{

    @Override
    public void service(Request request, Response response) {
        if("get".equals(request.getType())){
            this.doGet(request,response);
        }else if("post".equals(request.getType())){
            this.doPost(request,response);
        }
    }

    public void doGet(Request request, Response response){

    }

    public void doPost(Request request, Response response){

    }

}