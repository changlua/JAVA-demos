package com.changlu.java;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.listener.PingListener;

import java.util.HashMap;
import java.util.Map;

public class SocketIoServer
{
    public static void main(String[] args)
    {
        // 启动服务
        SocketIoServer sio = new SocketIoServer();
        sio.initSocket();
    }
    // 保存session与端映射关系
    public Map<String, SocketIOClient> clientMap = new HashMap<String, SocketIOClient>();
    /**
     * Description: 初始化
     * 
     * @see
     */
    public void initSocket()
    {
        Configuration config = new Configuration();
        // 一般不需要设置Hostname，设置localhost后，从其他IP连接会连不上来
        // config.setHostname("localhost");
        // 设置端口
        config.setPort(10015);
        // 往服务器写ping消息，用来检测客户端是否存活，如果设置时间内没有ping通，则清理掉客户端连接
        config.setPingTimeout(3000);
        // ping间隔时长
        config.setPingInterval(30000);
        // config.setBossThreads(5);
//        // config.setWorkerThreads(15);
        config.setAuthorizationListener(new AuthorizationListener()
        {
            @Override
            public boolean isAuthorized(HandshakeData handshakedata)
            {
                // 这里可以拦截连接过来的URL。可以获取参数等。比如统一鉴权就可以在这里处理
                System.out.println(handshakedata.getUrl());
                return true;
            }
        });
        SocketIOServer server = new SocketIOServer(config);
        // 添加一个命名空间，命名空间可以用于区分不同的业务连接
        SocketIONamespace namespace = server.addNamespace("/chat");
        server.start();
    }
    /**
     * Description: 添加监听程序
     * 
     * @param namespace
     * @see
     */
    public void addListener(SocketIONamespace namespace)
    {
        // 添加连接监听
        namespace.addConnectListener(new ConnectListener()
        {
            @Override
            public void onConnect(SocketIOClient client)
            {
                System.out.println(client.getRemoteAddress() + "连接上了");
                // 把连接上的客户端保存起来
                clientMap.put(client.getSessionId().toString(), client);
            }
        });
        // 添加连接断开监听
        namespace.addDisconnectListener(new DisconnectListener()
        {
            @Override
            public void onDisconnect(SocketIOClient client)
            {
                System.out.println(client.getRemoteAddress() + "离开了");
                clientMap.remove(client.getSessionId().toString());
            }
        });
        // 添加ping监听
        namespace.addPingListener(new PingListener()
        {
            @Override
            public void onPing(SocketIOClient socketioclient)
            {
                // 往客户端写ping消息，用来检测客户端是否存活
                System.out.println("ping:" + socketioclient.getRemoteAddress());
            }
        });
        // 添加自定义监听，例如：用户注册
        namespace.addEventListener("register_event", String.class, new DataListener<String>()
        {
            @Override
            public void onData(SocketIOClient client, String str, AckRequest ack)
                throws Exception
            {
                // 发送ack给客户端告知服务器端已经收到消息
                ack.sendAckData("ok");
            }
        });
    }
    public void sendMsg(String sessionId, String msg)
    {
        // 通过sessionid获取socket连接
        SocketIOClient client = clientMap.get(sessionId);
        // 发条消息给客户端。客户端监听的是message
        client.sendEvent("message", new AckCallback<Object>(Object.class, 5 /* 注意这里的单位是秒 */)
        {
            @Override
            public void onSuccess(Object result)
            {
                // 接收客户端返回的ack。意味着客户端已经接收到消息了
                System.out.println("ack from client: " + client.getSessionId() + " data: "
                                   + result);
            }
            @Override
            public void onTimeout()
            {
                System.out.println("ACK超时");
            }
        }, msg);
    }
}