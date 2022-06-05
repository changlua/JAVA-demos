package xyz.changlu;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Request
 * @Author ChangLu
 * @Date 2021/5/15 20:02
 * @Description TODO
 */
public class Request {
    //状态行
    private String type;//请求类型
    private String url;//地址
    private String protocol;//协议

    //请求头
    private Map<String,String> headers = new HashMap<>(8);
    //请求参数
    private Map<String,String> parameters = new HashMap<>(8);


    /**
     * 对报文进行封装成request对象
     * @param requestStr 报文字符串
     * @return 封装后的请求对象
     */
    public static Request buildRequest(StringBuilder requestStr){
        Request request = new Request();
        //①(状态行、请求头)+请求体
        String[] twoPartsArr = requestStr.toString().split("\r\n\r\n");
        //处理(状态行、请求头)
        String[] statusAndHeadersArr  = twoPartsArr[0].split("\r\n");
        String[] statusArr = statusAndHeadersArr[0].split(" ");//数组下标0为状态行，分割成三个数组
        if(statusArr.length == 3){
            request.setType(statusArr[0]);
            request.setUrl(statusArr[1]);
            request.setProtocol(statusArr[2]);
        }
        for (int i = 1;i<statusAndHeadersArr.length;i++){ //遍历数组下标为1-length的请求头
            String[] headers = statusAndHeadersArr[i].split(": ");//将单个请求键值对切分成key与value
            request.getHeaders().put(headers[0].trim(),headers[1].trim());//将键值对存储到HashMap中
        }

        //处理请求体(Post请求)
        if(twoPartsArr.length == 2){
            //封装请求体内容
        }

        return request;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", headers=" + headers +
                ", parameters=" + parameters +
                '}';
    }
}