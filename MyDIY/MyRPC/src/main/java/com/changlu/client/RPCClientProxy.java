package com.changlu.client;

import com.changlu.common.RPCRequestMessage;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RPCClientProxy
 * @Author ChangLu
 * @Date 5/31/2022 2:12 PM
 * @Description RPC调用客户端代理类
 */
@AllArgsConstructor
public class RPCClientProxy implements InvocationHandler {

    private RPCClient rpcClient;

    //并发计数器
    private static AtomicInteger seqId = new AtomicInteger(0);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        RPCRequestMessage request = RPCRequestMessage.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsType(method.getParameterTypes())
                .params(args).build();
        request.setSeqId(seqId.getAndIncrement());//设置唯一序列号
        //进行远程调用
        Object result = rpcClient.sendRequest(request);
        //处理POJO反序列化为LinkedTreeMap情况
        if (result instanceof LinkedTreeMap) {
            final String json = new Gson().toJson(result);
            return new Gson().fromJson(json, method.getReturnType());
        }
        return result;
    }

    /**
     * 获取一个RPC代理类
     */
    public <T> T getRPCProxy(Class<T> clazz){
        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) proxy;
    }

}
