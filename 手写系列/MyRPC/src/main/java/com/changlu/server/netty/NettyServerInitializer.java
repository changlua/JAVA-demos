package com.changlu.server.netty;

import com.changlu.protocol.ProcotolFrameDecoder;
import com.changlu.protocol.RPCCodec;
import com.changlu.server.handler.RpcRequestMessageHandler;
import io.netty.channel.*;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.AllArgsConstructor;

/**
 * @ClassName NettyServerInitializer
 * @Author ChangLu
 * @Date 5/31/2022 4:36 PM
 * @Description 初始化器：主要负责序列化的编码解码， 需要解决netty的粘包问题
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    private ServiceProvider serviceProvider;

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //自定义编码格式
        pipeline.addLast(new ProcotolFrameDecoder());
        //自定义编解码器
        pipeline.addLast(new RPCCodec());
        //心跳检测：设置6秒触发一个读事件，该事件就会进行一个channel关闭操作
        pipeline.addLast(new IdleStateHandler(6, 0, 0));
        pipeline.addLast(new ChannelDuplexHandler(){
            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                if (evt instanceof ChannelInputShutdownReadComplete) {
                    //channel连接已关闭
                    System.out.println(String.format("channel连接已关闭：%s", ctx.channel()));
                    ctx.channel().close();
                }
                //处理客户端直接关闭连接这边转换异常问题
                if (evt instanceof IdleStateEvent) {
                    IdleStateEvent event = (IdleStateEvent) evt;
                    //若是触发了一个读事件，关闭channel连接
                    if (event.state() == IdleState.READER_IDLE) {
                        System.out.println("===该channel已经6秒没有发送数据了，停止心跳...===");
                        ctx.channel().close();
                    }
                }
            }
        });
        //RPC处理器
        pipeline.addLast(new RpcRequestMessageHandler(serviceProvider));
        pipeline.addLast("server handler", new ChannelInboundHandlerAdapter() {

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel不活跃");
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
                System.out.println(String.format("连接已断开，请按任意键退出...，异常信息：%s", throwable.getMessage()));
            }
        });
    }
}
