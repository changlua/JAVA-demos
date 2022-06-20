# 简介
实现服务与服务之间的远程调用，让客户端调用服务端的接口与调用本机接口一样容易，手写
实现RPC框架的核心逻辑，使用Zookeeper作为注册中心，消费者从其获取服务地址来进行RPC调用。

# 当前实现功能

> 宏观视角

RPC过程：客户端【获取代理类执行方法(调用接口方法) -> 执行sendRequest -> 注册中心获取服务 -> request序列化-> 协议编码 -> 网络传输】 -> 服务端【反序列化->本地反射调用方法->序列化->编码传输】

服务端启动过程：服务注册->等待请求

> 功能细化

客户端、服务端：`基于Netty`

客户端：

+ 获取代理类执行方法(调用接口方法)：`JDK动态代理`
+ 执行sendRequest：`自定义Message消息类型`
  + channle获取：`懒汉式的单例写法`【当前channel设置为static，一旦为null就要进行初始化重新连接获取channel，后续可能会根据不同的服务做缓存来获取相应的channel】
+ 注册中心获取服务：`集成Zookeeper`【实现服务注册、服务发现功能】、`负载均衡`【根据获取到的地址列表实现随机策略、轮询策略】
+ request序列化：`JSON、JDK序列化`【根据配置指定的序列化类型进行执行序列化算法】
+ 协议编码：`自定义编码规则`如：唯一ID(4个字节) | 序列化号(1个字节) | 消息类型(1个字节) | 序列化内容长度(4个字节) | 序列化内容(不定长)、`LTC解码器`解决半包黏包问题 当前参数为 6 4 0 0【长度字段偏移量，长度字段长度，长度字段后偏移量，从头剥离几个字节】
+ 传输响应：`基于Netty的Promise、请求唯一ID`【阻塞存取响应结果】

服务端：

+ 反序列化：【自定义协议解码读取请求的序列化类型选择执行算法进行反序列化】
+ 本地反射调用方法：`反射`【根据序列化得到的Response对象的属性获取到相应的接口实例来进行反射调用】

服务端检测客户端连接的手段：`心跳检测`【基于Netty的IndleStateHandler来实现】

客户端服务端的连接关闭：

+ 客户端channel关闭：`实现ChannelInboundHandlerAdapter的channelInactive方法`【一旦触发表示连接关闭，那么就要关闭掉channel】
+ 服务端channel关闭：`实现ChannelDuplexHandler的userEventTriggered方法`【evt为IdleStateEvent类型的根据没有接收到客户端的channel心跳，关闭该channel】



# 使用方式

## 可配置项

1、当前仅有序列化配置：

![image-20220608163218591](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/6/202206081632647.png)  

## 前提准备

使用Docker来快速安装Zookeeper配置中心，可见博客：[01、认识Zookeeper与Win和Linux安装，看其中的linux安装部分](https://changlu.blog.csdn.net/article/details/125168328)

记得关闭防火墙。

## 使用方式

1、修改Zookeeper的一个对应地址：

![image-20220608163105628](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/6/202206081631749.png)  

2、首先启动服务提供者来进行服务注册

启动server/Main.java即可启动，若是出现rpc注册成功则没有问题：

![image-20220608163312754](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/6/202206081633885.png)  

看下虚拟机：

![image-20220608163458540](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/6/202206081634576.png)  

3、运行客户端来进行RPC调用

![image-20220608163601627](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/6/202206081636719.png)  

![image-20220608163734679](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/6/202206081637737.png)  





---

整理者：长路  时间：2022.6.8
