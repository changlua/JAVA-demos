package com.changlu.server.handler;

import com.changlu.common.RPCRequestMessage;
import com.changlu.common.RPCResponseMessage;
import com.changlu.server.netty.ServiceProvider;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

/**
 * @ClassName NettyRPCServerHandler
 * @Author ChangLu
 * @Date 5/31/2022 5:24 PM
 * @Description 处理RPC调用的专用处理器
 */
@AllArgsConstructor
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RPCRequestMessage> {

    private ServiceProvider serviceProvider;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCRequestMessage rpcRequestMessage) throws Exception {
        RPCResponseMessage response = getResponse(rpcRequestMessage);
        ctx.channel().writeAndFlush(response);
//        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private RPCResponseMessage getResponse(RPCRequestMessage request) {
        //1、得到服务名
        final String interfaceName = request.getInterfaceName();
        //2、从当前服务中获取到相应的接口实例
        Object service = serviceProvider.getService(interfaceName);
        //对于反射调用执行采用try-catch写法
        try {
            //注意：获取到某个方法需要相应的方法名以及参数类型数组
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsType());
            Object data = method.invoke(service, request.getParams());
            return RPCResponseMessage.success(request.getSeqId(), data);
        } catch (Exception e) {
            e.printStackTrace();
            return RPCResponseMessage.fail(request.getSeqId(), e);
        }
    }



}
