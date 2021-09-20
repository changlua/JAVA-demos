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

# 03、Mybatis(Mybatis源码手写相关)

## 03-1、Mybatis-spring

> 介绍

![image-20210823231932982](https://gitee.com/changluJava/picture-bed/raw/master/2021-8/20210823231933.png)  

**功能描述**：实现`Mybatis-spring`依赖中的@MapperScan功能，目的是将mybatis产生的代理类通过@MapperScan注入到Spring容器。

**参考视频**：[这可能是B站讲的最好的SPRING源码教程（2021年最新版）](https://www.bilibili.com/video/BV1dK4y127mH)中的36-40集，介绍了mybatis的代理类如何生成以及注入代理类到Spring容器！

**对应自己文档**：

![image-20210823232350297](https://gitee.com/changluJava/picture-bed/raw/master/2021-8/20210823232350.png)  

<br/>

> 运行项目

step1：导入sql文件

![image-20210823223602776](https://gitee.com/changluJava/picture-bed/raw/master/2021-8/20210823223602.png)  

```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `last_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `deleted` int(1) NULL DEFAULT 1 COMMENT '逻辑删除\r\n',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1428384446647291906 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'changlu', 22, '368', 1, NULL, '2021-08-19 00:26:56');
INSERT INTO `user` VALUES (2, 'Tom', 28, 'test3@baomidou.com', 1, NULL, '2021-08-19 00:26:56');
INSERT INTO `user` VALUES (3, 'Billie', 23, 'test5@baomidou.com', 1, NULL, '2021-08-19 00:27:25');
INSERT INTO `user` VALUES (4, 'xiaoli', 11, '86578954@qq.com', 1, NULL, '2021-08-19 00:26:56');
INSERT INTO `user` VALUES (1428010279540023298, 'xiaotian', 18, '86547528@qq.com', 0, NULL, '2021-08-19 00:26:56');
INSERT INTO `user` VALUES (1428037780849168385, 'mining', 28, '86547528@qq.com', 1, '2021-08-19 00:55:13', '2021-08-19 00:55:37');
INSERT INTO `user` VALUES (1428384446647291905, 'xiaomei', 20, '86547528@qq.com', 1, '2021-08-19 23:52:45', '2021-08-19 23:52:45');

SET FOREIGN_KEY_CHECKS = 1;
​```
```

step2：运行项目即可！

<br/>

---

# SpringSecurity

## 01、动态绑定用户权限接口

> 提前准备操作

创建一个test数据库，接着导入文件中的sql语句。

<br/>

> 说明

![image-20210920000541648](https://gitee.com/changluJava/picture-bed/raw/master/2021/20210920000541.png)  

通过在数据库定义用户、角色、权限三个表来动态的添加账号用户权限到spring security。

并且其中还集成了自定义login页面以及自定义403页面，使用的form表单认证形式。

**详细代码说明可见**：

![image-20210920121758637](https://gitee.com/changluJava/picture-bed/raw/master/2021/20210920121758.png)  

<br/>

---

## 02、springsecurity整合oauth2

**包含两个服务**：授权服务端、会员服务端。

整个模式与qq登陆、微信登陆、支付都是类似，通过appid、secret换取到code，接着服务器端拿到code值去得到accesstoken。

最终通过accesstoken来去访问一些会员接口。

**详细文档说明见**：

![image-20210920122031553](https://gitee.com/changluJava/picture-bed/raw/master/2021/20210920122031.png)  



<br/>

---

整理者：长路  时间：2021.5.16   最近更新时间：2021.9.20 12:21