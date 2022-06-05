package com.changlu.client.netty;

import com.changlu.client.handler.RpcResponseMessageHandler;
import com.changlu.protocol.ProcotolFrameDecoder;
import com.changlu.protocol.RPCCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


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
        //RPCResponse处理器
        pipeline.addLast(new RpcResponseMessageHandler());
    }
}
