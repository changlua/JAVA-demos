package com.changlu.client.netty;

import com.changlu.client.handler.RpcResponseMessageHandler;
import com.changlu.common.PingMessage;
import com.changlu.protocol.ProcotolFrameDecoder;
import com.changlu.protocol.RPCCodec;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * @ClassName NettyClientInitializer
 * @Author ChangLu
 * @Date 5/31/2022 5:55 PM
 * @Description Netty的初始化器
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //自定义编码格式
        pipeline.addLast(new ProcotolFrameDecoder());
        //自定义编解码器
        pipeline.addLast(new RPCCodec());
        //心跳检测：3秒没有去写数据
        pipeline.addLast(new IdleStateHandler(0, 3, 0));
        pipeline.addLast(new ChannelDuplexHandler(){
            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                //心跳检测事件（3秒没有发数据就会有尝试发送一个心跳）
                if (evt instanceof IdleStateEvent) {
                    IdleStateEvent event = (IdleStateEvent) evt;
                    //触发写事件：这里向服务方发送一个心跳，表示还要继续执行
                    if (event.state() == IdleState.WRITER_IDLE) {
                        System.out.println("===3秒没有发送数据了，向服务端发送一个心跳===");
                        ctx.channel().writeAndFlush(new PingMessage());
                    }
                }
            }
        });
        //RPCResponse处理器
        pipeline.addLast(new RpcResponseMessageHandler());
        //客户端处理器
        pipeline.addLast("client handler", new ChannelInboundHandlerAdapter() {

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel不活跃");
                //channel连接已关闭
                NettyRPCClient.closeChannel();
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
                System.out.println(String.format("连接已断开，请按任意键退出...，异常信息：%s", throwable.getMessage()));
            }
        });
    }
}
