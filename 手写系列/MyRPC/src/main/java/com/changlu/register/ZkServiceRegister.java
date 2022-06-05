package com.changlu.register;

import com.changlu.register.balance.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @ClassName ZkServiceRegister
 * @Author ChangLu
 * @Date 6/4/2022 12:32 PM
 * @Description zookeeper服务注册
 */
@Slf4j
public class ZkServiceRegister implements ServiceRegister {

    public static void main(String[] args) {
        ZkServiceRegister register = new ZkServiceRegister();
        register.register("user", new InetSocketAddress("localhost", 8080));
        System.out.println(register.serviceDiscovery("user"));
    }

    //curator 提供的 zookeeper客户端
    private CuratorFramework client;
    private LoadBalance loadBalance;

    //Zookeeper的根路径节点
    private static final String ROOT_PATH = "MYRPC";

    public ZkServiceRegister(){
        //设置策略：重试次数为3次，每次间隔1s
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("192.168.10.40:2181")  //连接地址
                .sessionTimeoutMs(40000) //建立会话时常
                .retryPolicy(policy)    //设置重试策略
                .namespace(ROOT_PATH)   //设置路径
                .build();               //构建
        //进行客户端连接
        this.client.start();
        System.out.println("zookeeper 连接成功");
    }

    public ZkServiceRegister(LoadBalance loadBalance) {
        this();
        this.loadBalance = loadBalance;
    }

    @Override
    public void register(String serviceName, InetSocketAddress serverAddress) {
        try {
            //若是当前服务端没有该路径进行注册
            if (client.checkExists().forPath("/" + serviceName) == null) {
                //CreateMode.PERSISTENT：持久性
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/" + serviceName);
            }
            //注册节点，例如：/serviceName/xxx
            String path = "/" + serviceName + "/" + getServiceAddress(serverAddress);
            //CreateMode.EPHEMERAL：临时型。连接下线时就需要删除节点
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
            System.out.println("服务名为：" + serviceName + " 注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("此服务已存在，无需再注册");
        }
    }

    //根据服务名返回地址
    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        List<String> strings = null;
        try {
            strings = client.getChildren().forPath("/" + serviceName);
            if (strings == null || strings.size() == 0) {
                throw new RuntimeException("目前无服务提供！");
            }
            //根据当前是否有负载均衡策略器，若是有则进行负载均衡选择
            String choose;
            if (this.loadBalance == null ) {
                //当前默认使用第一个
                choose = strings.get(0);
            }else {
                choose = loadBalance.balance(strings);
            }
            return parseAddress(choose);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据地址对象获取服务名及端口：地址 => xxx.xxx.xxx.xxx:port
    private String getServiceAddress(InetSocketAddress serverAddress) {
        return serverAddress.getHostName() +
                ":" +
                serverAddress.getPort();
    }

    //字符串解析为地址对象
    private InetSocketAddress parseAddress(String address) {
        String[] result = address.split(":");
        return new InetSocketAddress(result[0], Integer.parseInt(result[1]));
    }

}
