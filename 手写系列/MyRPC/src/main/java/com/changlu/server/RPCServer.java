package com.changlu.server;

/**
 * @ClassName RPCServer
 * @Author ChangLu
 * @Date 5/31/2022 3:41 PM
 * @Description Server接口
 */
public interface RPCServer {
    void start(int port);

    void stop();
}
