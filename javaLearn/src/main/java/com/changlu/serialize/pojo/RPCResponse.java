package com.changlu.serialize.pojo;

/**
 * @ClassName RPCResponse
 * @Author ChangLu
 * @Date 6/14/2022 8:39 PM
 * @Description RPC响应实体类
 */
public class RPCResponse<T> extends Message{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RPCResponse{" +
                "data=" + data +
                '}';
    }
}
