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