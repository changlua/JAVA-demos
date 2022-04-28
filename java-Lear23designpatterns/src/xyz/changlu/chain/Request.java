package xyz.changlu.chain;

/**
 * @ClassName Request
 * @Author ChangLu
 * @Date 2021/3/20 19:15
 * @Description TODO
 */
//实例对象：通过其中属性来判定其中的执行是否有效
public class Request {

    private boolean requestFrequency;//请求频率
    private boolean loginAuthentication;//登陆认证
    private boolean accessPermission;//访问权限

    public Request(RequestBuilder builder){
        this.requestFrequency = builder.requestFrequency;
        this.loginAuthentication = builder.loginAuthentication;
        this.accessPermission = builder.accessPermission;
    }

    static class RequestBuilder{
        private boolean requestFrequency;//请求频率
        private boolean loginAuthentication;//登陆认证
        private boolean accessPermission;//访问权限

        public RequestBuilder setRequestFrequency(boolean requestFrequency){
            this.requestFrequency = requestFrequency;
            return this;
        }

        public RequestBuilder setLoginAuthentication(boolean loginAuthentication){
            this.loginAuthentication = loginAuthentication;
            return this;
        }

        public RequestBuilder setAccessPermission(boolean accessPermission){
            this.accessPermission = accessPermission;
            return this;
        }

        public Request build(){
            return new Request(this);
        }
    }

    public boolean isRequestFrequency() {
        return requestFrequency;
    }

    public boolean isLoginAuthentication() {
        return loginAuthentication;
    }

    public boolean isAccessPermission() {
        return accessPermission;
    }
}
