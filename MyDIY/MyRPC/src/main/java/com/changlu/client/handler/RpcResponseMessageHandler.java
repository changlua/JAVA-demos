package com.changlu.client.handler;

import com.changlu.common.RPCResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RpcResponseMessageHandler
 * @Author ChangLu
 * @Date 5/31/2022 6:00 PM
 * @Description 客户端RPCRequest处理器
 */
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RPCResponseMessage> {

    /**
     * 临时存储对应唯一id的promise
     */
    public static Map<Integer, Promise> promiseMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCResponseMessage rpcResponseMessage){
        Object value = rpcResponseMessage.getReturnValue();
        Exception ex = rpcResponseMessage.getExceptionValue();
        Promise promise = promiseMap.remove(rpcResponseMessage.getSeqId());
        if (ex != null ) {
            promise.setFailure(ex);
        }
        promise.setSuccess(value);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
