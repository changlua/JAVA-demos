package com.changlu.client;


import com.changlu.common.RPCRequestMessage;

/**
 * @ClassName RPCClient
 * @Author ChangLu
 * @Date 5/31/2022 2:07 PM
 * @Description RPC客户端调用接口
 */
public interface RPCClient {

    Object sendRequest(RPCRequestMessage request);

}
