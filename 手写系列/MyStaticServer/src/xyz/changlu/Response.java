package xyz.changlu;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Response
 * @Author ChangLu
 * @Date 2021/5/15 20:02
 * @Description TODO
 */
public class Response {
    //状态行
    private String protocol = "HTTP/1.1";
    private Integer status = 200;
    private String msg = "ok";

    //响应字段
    private Map<String,String> headers = new HashMap<String,String>(){  //第一个{}是匿名初始化
        //实例初始化块,该内部匿名类构造时被执行
        {
            put("Content-Type","text/html;charset=utf-8");
        }
    };
    private String contentType = "text/html;charset=utf-8";//内容类型
    private String contentLength;//内容长度
    //data数据
    private String data;


    //返回响应的字符串
    public String getResponseStr(){
        StringBuilder strBuilder = new StringBuilder();
        //封装状态行
        strBuilder.append(getProtocol()+" ")
                .append(getStatus()+" ")
                .append(getMsg()+"\r\n");
        //封装响应行
        for (Map.Entry<String,String> entry:headers.entrySet()){//遍历键值对
            strBuilder.append(entry.getKey()+": "+entry.getValue()+"\r\n");
        }
        //封装空行
        strBuilder.append("\r\n");
        //封装响应数据
        strBuilder.append(getData());
        return strBuilder.toString();
    }

    /**
     * 进行响应操作
     * @param os 传入输出流
     */
    public void write(OutputStream os){
        try {
            //直接进行输出流响应
            os.write(getResponseStr().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加响应头
     * @param key 键
     * @param value 值
     */
    public void addHeader(String key,String value){
        this.headers.put(key,value);
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
        this.headers.put("Content-Type",this.contentType);
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
        this.headers.put("Content-Length",this.contentLength);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        this.setContentLength(String.valueOf(data.getBytes().length));//设置数据时对长度进行赋值
    }

    @Override
    public String toString() {
        return "Response{" +
                "protocol='" + protocol + '\'' +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", headers=" + headers +
                ", data='" + data + '\'' +
                '}';
    }
}