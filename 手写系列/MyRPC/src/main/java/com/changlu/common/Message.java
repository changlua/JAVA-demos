package com.changlu.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Message
 * @Author ChangLu
 * @Date 5/31/2022 9:45 PM
 * @Description 消息抽象类
 */
public abstract class Message implements Serializable {

    private int seqId;

    private int messageType;


    //静态参数
    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();

    //消息标识
    public static final int RPC_MESSAGE_TYPE_REQUEST = 101;
    public static final int RPC_MESSAGE_TYPE_RESPONSE = 102;
    public static final int PING_MESSAGE_REQUEST = 103;//心跳消息

    static {
        messageClasses.put(RPC_MESSAGE_TYPE_REQUEST, RPCRequestMessage.class);
        messageClasses.put(RPC_MESSAGE_TYPE_RESPONSE, RPCResponseMessage.class);
        messageClasses.put(PING_MESSAGE_REQUEST, PingMessage.class);
    }

    public abstract int getMessageType();

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    public int getSeqId() {
        return seqId;
    }

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }
}
