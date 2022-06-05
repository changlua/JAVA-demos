package com.changlu.common;

import lombok.Builder;
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
public class RPCResponse extends Message {

    private Object returnValue;

    private Exception exceptionValue;

    public static RPCResponse fail(int seqId, Exception e) {
         RPCResponse response = new RPCResponse();
         //重新封装好异常信息后返回
         response.setExceptionValue(new Exception(e.getCause().getMessage()));
         response.setSeqId(seqId);
         return response;
    }

    public static RPCResponse success(int seqId, Object val) {
        RPCResponse response = new RPCResponse();
        response.setReturnValue(val);
        response.setSeqId(seqId);
        return response;
    }

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
