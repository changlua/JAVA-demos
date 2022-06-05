package com.changlu.protocol;

import com.changlu.common.Message;
import com.changlu.config.Config;
import com.changlu.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @ClassName RPCCodec
 * @Author ChangLu
 * @Date 5/31/2022 9:31 PM
 * @Description RPC编解码协议
 */
public class RPCCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        //1、4个字节——唯一ID
        byteBuf.writeInt(message.getSeqId());
        //2、1个字节——配置文件配置的序列化类型
        byteBuf.writeByte(Config.serializerType);
        //3、1个字节——消息类型
        byteBuf.writeByte(message.getMessageType());
        //4、序列化内容长度
        Serializer serializer = Serializer.getSerializerByType(Config.serializerType);
        byte[] data = serializer.serialize(message);//进行序列化操作
        byteBuf.writeInt(data.length);
        //4、内容
        byteBuf.writeBytes(data);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List list) throws Exception {
        //1、4个字节——唯一ID
        int seqId = in.readInt();
        //2、1个字节——配置文件配置的序列化类型
        byte serializeType = in.readByte();
        //3、1个字节——消息类型
        byte messageType = in.readByte();
        //3、序列化内容长度
        int length = in.readInt();
        byte[] data = new byte[length];
        //4、内容
        in.readBytes(data, 0, length);
        //进行反序列化
        Serializer serializer = Serializer.getSerializerByType(serializeType);
        if (serializer == null) {
            throw new RuntimeException("无序列化方式");
        }
        Object message = serializer.deserialize(Message.getMessageClass(messageType), data);
        list.add(message);
    }
}
