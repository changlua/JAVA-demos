package com.changlu.serialize.pojo;

import java.io.Serializable;

/**
 * @ClassName Message
 * @Author ChangLu
 * @Date 6/14/2022 8:39 PM
 * @Description 消息类
 */
public class Message implements Serializable {

    private int messageType;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
