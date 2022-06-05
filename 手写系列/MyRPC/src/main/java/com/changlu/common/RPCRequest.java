package com.changlu.common;

import lombok.Builder;
import lombok.Data;


/**
 * @ClassName RPCRequest
 * @Author ChangLu
 * @Date 5/31/2022 10:41 AM
 * @Description TODO
 */
@Data
@Builder
public class RPCRequest extends Message {

    private String interfaceName;

    private String methodName;

    private Class<?>[] paramsType;

    private Object[] params;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_REQUEST;
    }
}
