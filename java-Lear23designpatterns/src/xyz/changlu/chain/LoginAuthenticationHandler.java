package xyz.changlu.chain;

/**
 * @ClassName loginAuthenticationHandler
 * @Author ChangLu
 * @Date 2021/3/20 20:18
 * @Description TODO
 */
//登陆认证处理器
public class LoginAuthenticationHandler extends Handler{

    public LoginAuthenticationHandler(Handler handler) {
        super(handler);
    }

    @Override
    boolean process(Request request) {
        System.out.println("开始执行登陆验证....");
        //1、判断登陆验证是否通过
        if(request.isLoginAuthentication()){
            System.out.println("登陆验证通过！");
            Handler handler = this.getNext();
            //判断是否有下个执行器(本案例无)
            if (handler != null) {
                //2、判断是下个执行器是否通过(本案例是不会执行到这里的)
                if (!handler.process(request)) {
                    return false;
                }
            }
            return true;
        }
        //登陆认证失败
        System.out.println("登陆认证失败!");
        return false;
    }
}
