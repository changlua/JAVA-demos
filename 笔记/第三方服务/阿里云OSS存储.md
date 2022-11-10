[toc]



# 前言

[OSS Java SDK 3.13.2版本—阿里官方文档](https://help.aliyun.com/document_detail/32008.htm?spm=a2c4g.11186623.0.0.286d24cbqxOR3e#concept-32008-zh)：可以看其中的起步案例

[阿里云-OSS对象存储](https://oss.console.aliyun.com/overview)

# 学习

## GetStartedSample（初始demo）

已调试其中的带中文注释

```java
package com.changlu.test.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

/**
 * This sample demonstrates how to get started with basic requests to Aliyun OSS 
 * using the OSS SDK for Java.
 */
public class GetStartedSample {
    
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI5tP8dKzxm3kDPqTUQboJ";
    private static String accessKeySecret = "UU3yATawFsOEtDgMhhktlum8SqPt70";
//    private static String bucketName = "pictured-bed";
    private static String bucketName = "pictured-bedtest";
    private static String key = "test/test2/";//若是目录，一定要设置/

    //方便之后拼接
    private static String shortEndpoint = "oss-cn-beijing.aliyuncs.com";
    
    public static void main(String[] args) throws IOException {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        
        System.out.println("Getting Started with OSS SDK for Java\n");
        
        try {

//            /*
//             * Determine whether the bucket exists 查看桶是否存在
//             */
//            if (!ossClient.doesBucketExist(bucketName)) {  //创建桶
//                /*
//                 * Create a new OSS bucket
//                 */
//                System.out.println("Creating bucket " + bucketName + "\n");
//                ossClient.createBucket(bucketName);//根据当前的认证信息来进行创建Bucket桶，默认是私有
//                CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
//                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);//设置读写权限为公开读
//                ossClient.createBucket(createBucketRequest);//来
//            }

            /*
             * List the buckets in your account：获取所有的桶名称
             */
//            System.out.println("Listing buckets");
//
//            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
//            listBucketsRequest.setMaxKeys(500);
//
//            for (Bucket bucket : ossClient.listBuckets()) { //发起请求
//                System.out.println(" - " + bucket.getName());
//            }
//            System.out.println();
//
            /*
             * 上传文件
             * Upload an object to your bucket：上传对象到你的桶中
             */
//            System.out.println("Uploading a new object to OSS from a file\n");
//            File file = new File("C:\\Users\\93997\\Desktop\\桌面壁纸\\1.png");
//            String saveFilePath = key + file.getName();//拼接目标路径 + 图片名称
//            //第一个参数为：桶的名称；第二个参数为路径+上你的文件名，如test/1.png，若是test/test2/1.png，也会给我们自动创建目录
//            //第三个参数为：文件对象
//            ossClient.putObject(new PutObjectRequest(bucketName,saveFilePath , file));
//            //拼接获取公共访问地址
//            String publicVisitPath = "http://" + bucketName + '.' +  shortEndpoint + "/" + saveFilePath;
//            System.out.println("访问路径：" + publicVisitPath);
//
//            /*
//             * 删除文件
//             * Delete an object：删除对象
//             */
            System.out.println("Deleting an object\n");
            File file = new File("C:\\Users\\93997\\Desktop\\桌面壁纸\\1.png");
            String saveFilePath = key + file.getName();//拼接目标路径 + 图片名称
            ossClient.deleteObject(bucketName, saveFilePath);

//            /*
//             * Determine whether an object residents in your bucket：判断对象是否存在你的桶
//             */
//            boolean exists = ossClient.doesObjectExist(bucketName, key);
//            System.out.println("Does object " + bucketName + " exist? " + exists + "\n");
//
//            ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
//            ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.Default);
//
//            ObjectAcl objectAcl = ossClient.getObjectAcl(bucketName, key);
//            System.out.println("ACL:" + objectAcl.getPermission().toString());
//
//            /*
//             * Download an object from your bucket
//             */
//            System.out.println("Downloading an object");
//            OSSObject object = ossClient.getObject(bucketName, key);
//            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
//            displayTextInputStream(object.getObjectContent());
//
//            /*
//             * List objects in your bucket by prefix
//             */
//            System.out.println("Listing objects");
//            ObjectListing objectListing = ossClient.listObjects(bucketName, "My");
//            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//                System.out.println(" - " + objectSummary.getKey() + "  " +
//                                   "(size = " + objectSummary.getSize() + ")");
//            }
//            System.out.println();
//

            
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }
    }
    
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();
        
        reader.close();
    }

}
```

---

# 自定义封装

**上传、删除**方法已经封装到Utils中，controller只需要调用工具类的上传、删除即可！

![image-20220326184703797](https://pictured-bed.oss-cn-beijing.aliyuncs.com/img/2022/3/202203261847833.png)  

application.yml：

```
# 图片文件上传
spring:
  servlet:
    multipart:
      max-file-size: 100MB
      max-request-size: 1000MB

# 阿里云OSS配置
aliyun:
  oss:
    endpoint:   # 坐标地区示例：oss-cn-beijing.aliyuncs.com
    accessKeyId:  # 注册用户的keyID
    accessKeySecret:   # 注册用户的密钥
    bucketName:   # 桶名称 示例：pictured-st
    key:     # 存储根路径，例如：img/，以目录形式为结尾，之后图片会自动生成并添加到后缀
```

```java
package com.changlu.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AliyunOssConfig
 * @Author ChangLu
 * @Date 3/26/2022 4:22 PM
 * @Description 阿里云OOS配置
 */
@Component
// 指定配置文件中自定义属性前缀
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
@ToString
public class AliyunOssConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String key;

}
```

```java
package com.changlu.controller;

import com.changlu.domain.ResponseResult;
import com.changlu.utils.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UploadController
 * @Author ChangLu
 * @Date 3/26/2022 5:23 PM
 * @Description Oss文件控制器
 */
@RestController
public class OssFileController {

    @Autowired
    private AliyunOssUtil aliOssUtil;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("file")MultipartFile file){
       try {
           String visitUrl = aliOssUtil.uploadFile(file);
           Map<String,String> result = new HashMap<>(1);
           result.put("visitUrl", visitUrl);
           return new ResponseResult(200,"上传成功！", result);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseResult(500,"上传失败！");
       }
    }

    /**
     * 删除图片
     * @param fileName 文件名称如：b8809d28-82ec-4b06-af5f-8d3d7a16107c.jpg
     * @return
     */
    @GetMapping("/deleFile/{fileName}")
    public ResponseResult deleteFile(@PathVariable("fileName")String fileName){
        try {
            aliOssUtil.deleteFile(fileName);
            return new ResponseResult(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500,"删除失败！");
        }
    }

}
```

```
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author changlu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
```

```
package com.changlu.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.changlu.config.AliyunOssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName AliyunOssUtil
 * @Author ChangLu
 * @Date 3/26/2022 4:51 PM
 * @Description OSS对象存储工具类
 */
@Component
public class AliyunOssUtil {

    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    /**
     * 创建一个OSS连接
     * @return OssClient
     */
    private OSS createOssClient(){
        String endpoint = "http://" + aliyunOssConfig.getEndpoint();
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 关闭连接
     * @param ossClient
     */
    private void close(OSS ossClient){
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }

    /**
     * 上传文件
     * @param file File类型
     * @return
     */
    public String uploadFile(File file){
        String key = aliyunOssConfig.getKey();
        String bucketName = aliyunOssConfig.getBucketName();
        String endpoint = aliyunOssConfig.getEndpoint();
        //1、取得保存文件路径saveFilePath：拼接目标路径 + 图片名称
        String originFileName = file.getName();
        String filePrefix = UUID.randomUUID().toString();
        String saveFileName = filePrefix + originFileName.substring(file.getName().indexOf("."));
        String saveFilePath = key + saveFileName;
        OSS ossClient = null;
        try {
            ossClient = createOssClient();
            //2、上传文件
            //第一个参数为：桶的名称；第二个参数为路径+上你的文件名，如test/1.png，若是test/test2/1.png，也会给我们自动创建目录
            //第三个参数为：文件对象
            ossClient.putObject(new PutObjectRequest(bucketName,saveFilePath , file));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            this.close(ossClient);
        }
        //3、拼接获取公共访问地址
        String publicVisitPath = "http://" + bucketName + '.' +  endpoint + "/" + saveFilePath;
        return publicVisitPath;
    }

    /**
     * 上传文件
     * @param file MultipartFile类型
     * @return
     */
    public String uploadFile(MultipartFile file)throws Exception{
        String key = aliyunOssConfig.getKey();
        String bucketName = aliyunOssConfig.getBucketName();
        String endpoint = aliyunOssConfig.getEndpoint();
        //1、取得保存文件路径saveFilePath：拼接目标路径 + 图片名称
        String originFileName = file.getOriginalFilename();
        String filePrefix = UUID.randomUUID().toString();
        String saveFileName = filePrefix + originFileName.substring(originFileName.indexOf("."));
        String saveFilePath = key + saveFileName;
        OSS ossClient = null;
        try {
            ossClient = createOssClient();
            //2、上传文件
            //第一个参数为：桶的名称；第二个参数为路径+上你的文件名，如test/1.png，若是test/test2/1.png，也会给我们自动创建目录
            //第三个参数为：文件对象
            /**
             * 阿里云OSS 默认图片上传ContentType是image/jpeg
             * 图片链接后，图片是下载链接，而并非在线浏览链接
             * 这里在上传的时候要解决ContentType的问题，将其改为image/jpg
             */
            InputStream is = file.getInputStream();
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");
            ossClient.putObject(new PutObjectRequest(bucketName,saveFilePath , is, meta));
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            this.close(ossClient);
        }
        //3、拼接获取公共访问地址
        String publicVisitPath = "http://" + bucketName + '.' +  endpoint + "/" + saveFilePath;
        return publicVisitPath;
    }


    /**
     * 删除文件
     * @param fileName 待删除的文件名
     * @return
     */
    public void deleteFile(String fileName)throws Exception{
        String key = aliyunOssConfig.getKey();
        String bucketName = aliyunOssConfig.getBucketName();
        //1、拼接待删除路径
        String deleteFilePath = key + fileName;
        OSS ossClient = null;
        try {
            ossClient = createOssClient();
            //2、删除文件
            ossClient.deleteObject(bucketName, deleteFilePath);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            this.close(ossClient);
        }
    }

}
```



# 注意点

1、每次使用完OSSClient后要进行关闭，ossClient.shutdown()。

[OSSClient不关闭，导致大量FullGC](https://blog.csdn.net/ashur619/article/details/82835662)

2、可不可以设置OSSClient为单例？

+ new OSSClient只是初始化一堆对象，并未建立长连接，如果你endpoint和权限是固定的，定义成static变量都行，只有在有实际操作时，才会httpClient.execute()，我每次都new这个客户端，是因为包装sdk给业务用时，CredentialsProvider这个是动态的
+ 建议在方法中创建OSSClient 而不是使用@Bean注入，不然容易出现Connection pool shut down。



# 参考资料

[1] [一小时学会使用springboot操作阿里云OSS实现文件上传，下载，删除(附源码)](https://blog.csdn.net/weixin_43591980/article/details/109412083?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164827874016780366598080%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164827874016780366598080&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~times_rank-7-109412083.142^v5^pc_search_result_cache,143^v6^register&utm_term=SpringBoot%E6%95%B4%E5%90%88%E9%98%BF%E9%87%8C%E4%BA%91OSS&spm=1018.2226.3001.4187)

[2] [SpringBoot整合阿里云OSS](https://blog.csdn.net/u010953816/article/details/123357047?utm_source=app&app_version=5.2.1&code=app_1562916241&uLinkId=usr1mkqgl919blen)