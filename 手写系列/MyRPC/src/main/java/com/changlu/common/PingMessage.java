package com.changlu.common;

/**
 * @ClassName PingMessage
 * @Author ChangLu
 * @Date 6/7/2022 5:54 PM
 * @Description 心跳消息
 */
public class PingMessage extends Message{
    @Override
    public int getMessageType() {
        return PING_MESSAGE_REQUEST;
    }
}
