package xyz.changlu.chain;

import org.junit.Test;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/20 20:22
 * @Description TODO
 */
//测试类
public class Customer {
    public static void main(String[] args) {
        //设置request对象的属性
        Request request = new Request.RequestBuilder().setRequestFrequency(false).setLoginAuthentication(false).build();
        //将登陆认证执行放置到请求频率执行器之后
        RequestFrequencyHandler requestFrequencyHandler = new RequestFrequencyHandler(new LoginAuthenticationHandler(null));
        //开始执行
        requestFrequencyHandler.process(request);
    }

    @Test
    public void test02(){
        //设置request对象的属性
        Request request = new Request.RequestBuilder().setRequestFrequency(true).setLoginAuthentication(true).build();
        //将登陆认证执行放置到请求频率执行器之后
        RequestFrequencyHandler requestFrequencyHandler = new RequestFrequencyHandler(new LoginAuthenticationHandler(null));
        //开始执行
        requestFrequencyHandler.process(request);
    }
}
