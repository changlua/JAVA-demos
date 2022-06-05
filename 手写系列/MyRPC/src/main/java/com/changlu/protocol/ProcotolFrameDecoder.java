package com.changlu.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @ClassName ProcotolFrameDecoder
 * @Author ChangLu
 * @Date 5/31/2022 9:26 PM
 * @Description 自定义协议解码器（基于长度字段的帧解码器）
 */
public class ProcotolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProcotolFrameDecoder() {
        //序列化类型(1个字节) | 长度(4个字节) | 序列化内容
        this(Integer.MAX_VALUE, 6, 4, 0, 0);
    }

    public ProcotolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
