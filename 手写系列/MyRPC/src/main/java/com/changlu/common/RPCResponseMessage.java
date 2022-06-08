package com.changlu.common;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName RPCResponse
 * @Author ChangLu
 * @Date 5/31/2022 10:43 AM
 * @Description rpc的响应类
 */
@Data
@NoArgsConstructor
public class RPCResponseMessage extends Message {

    private Object returnValue;

    private Exception exceptionValue;

    public static RPCResponseMessage fail(int seqId, Exception e) {
         RPCResponseMessage response = new RPCResponseMessage();
         //重新封装好异常信息后返回
         response.setExceptionValue(new Exception(e.getCause().getMessage()));
         response.setSeqId(seqId);
         return response;
    }

    public static RPCResponseMessage success(int seqId, Object val) {
        RPCResponseMessage response = new RPCResponseMessage();
        response.setReturnValue(val);
        response.setSeqId(seqId);
        return response;
    }

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
