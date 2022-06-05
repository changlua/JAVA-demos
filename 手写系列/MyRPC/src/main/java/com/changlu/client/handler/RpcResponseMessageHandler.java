package com.changlu.client.handler;

import com.changlu.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Promise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RpcResponseMessageHandler
 * @Author ChangLu
 * @Date 5/31/2022 6:00 PM
 * @Description 客户端RPCRequest处理器
 */
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RPCResponse> {

    /**
     * 临时存储对应唯一id的promise
     */
    public static Map<Integer, Promise> promiseMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCResponse rpcResponse){
        Object value = rpcResponse.getReturnValue();
        Exception ex = rpcResponse.getExceptionValue();
        Promise promise = promiseMap.remove(rpcResponse.getSeqId());
        if (ex != null ) {
            promise.setFailure(ex);
        }
        promise.setSuccess(value);
        //关闭对应的channel
//        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
