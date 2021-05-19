# 01、MyStaticServer(手写静态服务器简单实现)

## 介绍

手写静态资源服务器参考视频：[IT楠老师-手写服务器](https://www.bilibili.com/video/BV13X4y1K7zc?p=22)

资源路径(当前目录)：`01、MyStaticServer`

![image-20210517215450808](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210517215451.png)  

+ `Server`：主类，用于启动服务器。
+ `MsgRunnable`：自定义线程类，用于处理接收到的请求。
+ `Request`、`Response`：封装的请求响应对象。
+ `HttpUtils`：工具类，两个方法一个是获取指定路径下的文件资源转成String、另一个是封装响应的数据。

**服务器说明**：当前仅仅只是一个静态资源访问服务器，可访问http://localhost:8899/xxx.html 的静态页面。

<br/>

---

## 测试服务器

### ①idea中运行

编译后需要在指定目录下添加pages目录，接着将html页面放入即可。

![image-20210516095929265](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210516095929.png)  

**运行效果**：

![image-20210516100018990](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210516100019.png)  

<br/>

---

### ②项目打jar包测试

> 下面该目录文件在本仓库中的`MyStaticServer/jartest`目录中，直接使用即可！
>
> 下面在windows环境中测试，linux同样是该目录，执行startup.sh启动！

将打好的jar包放置到文件目录下，在当前目录中添加一个`pages`文件，其中添加静态资源即可！

![image-20210516100548827](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210516100548.png)  

双击startup.bat即可启动服务器。

测试效果：

![image-20210516100837947](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210516100838.png)  

<br/>

---

# 02、MyServet(手写Tomcat中的Servlet容器)

## 介绍

手写Servlet容器参考视频：[【2021就业班】javaweb深入讲解，手写servlet容器，持续更新中...](https://www.bilibili.com/video/BV1GA411H7gt?p=4)

资源路径(当前目录)：`02、MyServlet`

![image-20210517215929765](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210517215930.png)  

+ `servlet`目录：包含一个servlet接口，实现接口的GenericServlet，继承GenericServlet的HttpServlet(封装了doGet()以及doPost()方法)。Request与Response是封装的请求与响应。
+ `controller`目录：包含多个servlet，用来封装业务。
+ `catalina`：启动类，用于启动服务器，监听请求等。
+ `container`：可以看做是Servlet容器，包含了多个Servlet。
+ 配置文件`web.properties`：包含了url以及对应的servlet全类名路径的键值对。

**服务器说明**：仿写Tomcat中的Servlet，可根据url配对指定的servlet，并根据请求方式来执行doGet或doPost方法。

<br/>

---

## 测试服务器

配置文件`web.properties`：存储了对应的请求地址与servlet的关系

```
/index=xyz.changlu.controller.IndexServlet
/home=xyz.changlu.controller.HomeServlet
/info=xyz.changlu.controller.IndexServlet
```

每当一个请求来临时，根据指定的url来获取到指定的servlet并执行请求。

> 测试

启动`catalina.java`

![image-20210518090348538](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210518090348.png)  

**访问请求**：

![image-20210518090437427](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210518090437.png)  

![image-20210518090450990](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210518090451.png)  

<br/>

---

# 03、apache-tomcat-8.5.66-src(tomcat源码，可以本地跑)

**核心源码讲解**：[手撕 tomcat 核心源码15讲【全网最详细tomcat 源码解析，底层原理 】](https://www.bilibili.com/video/BV1w64y1i7pg?p=1)

<br/>

> 本地运行Tomcat方式：使用本目录下的/03、apache-tomcat-8.5.66-src工程文件即可

IDEA加载之后，修改`VM-options`的参数

![image-20210519202428701](https://gitee.com/changluJava/picture-bed/raw/master/2021-5/20210519202428.png)  

```java
-Dcatalina.home=C:\Users\93997\Desktop\apache-tomcat-8.5.66-src\source 
-Dcatalina.base=C:\Users\93997\Desktop\apache-tomcat-8.5.66-src\source 
-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager 
-Djava.util.logging.config.file=C:\Users\93997\Desktop\apache-tomcat-8.5.66-src\source\conf\logging.properties
```

修改的内容就是其中`source`路径！







<br/>

---

整理者：长路  时间：2021.5.16