package com.changlu.server.netty;

import com.changlu.protocol.ProcotolFrameDecoder;
import com.changlu.protocol.RPCCodec;
import com.changlu.server.handler.RpcRequestMessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
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
        //RPC处理器
        pipeline.addLast(new RpcRequestMessageHandler(serviceProvider));
    }
}
