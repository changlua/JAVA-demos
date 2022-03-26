package com.changlu.test.common;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
//import com.google.common.collect.Maps;
import lombok.Data;
 
@Slf4j
@Component
public class OssObjectStorage extends BaseObjectStorage {
 
  @Data
  @Component
  @ConfigurationProperties(prefix = "oss")
  public static class OssInfo {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String rootDirectory;
  }
 
  @Autowired
  private OssInfo ossInfo;
 
  @Override
  public void upload(String pathAndName, File file) {
    OSS ossClient = new OSSClientBuilder().build(ossInfo.endpoint, ossInfo.accessKeyId,
        ossInfo.accessKeySecret);
    try {
      ossClient.putObject(
          new PutObjectRequest(ossInfo.bucketName, ossInfo.rootDirectory + pathAndName, file));
    } finally {
      ossClient.shutdown();
    }
 
  }
 
  @Override
  public String authorize(String pathAndName, long time) {
    OSS ossClient = new OSSClientBuilder().build(ossInfo.endpoint, ossInfo.accessKeyId,
        ossInfo.accessKeySecret);
    try {
      Date expiration = new Date(System.currentTimeMillis() + time);
      URL url = ossClient.generatePresignedUrl(ossInfo.bucketName,
          ossInfo.rootDirectory + pathAndName, expiration);
      return url.toString();
    } finally {
      ossClient.shutdown();
    }
  }
 
  @Override
  public String authorizeAllName(String pathAndName, long time) {
    OSS ossClient = new OSSClientBuilder().build(ossInfo.endpoint, ossInfo.accessKeyId,
        ossInfo.accessKeySecret);
    try {
      Date expiration = new Date(System.currentTimeMillis() + time);
      URL url = ossClient.generatePresignedUrl(ossInfo.bucketName, pathAndName, expiration);
      return url.toString();
    } finally {
      ossClient.shutdown();
    }
  }
 
  @Override
  public Map<String, Object> tokens(String dir) {
//    Map<String, Object> result = Maps.newHashMap();
    Map<String, Object> result = new HashMap<>();
    OSS ossClient = new OSSClientBuilder().build(ossInfo.endpoint, ossInfo.accessKeyId,
        ossInfo.accessKeySecret);
    try {
      long expireTime = 60;
      long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
      Date expiration = new Date(expireEndTime);
      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
      dir = "frontend/xhh/" + dir;
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
      String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes("utf-8");
      String encodedPolicy = BinaryUtil.toBase64String(binaryData);
      String postSignature = ossClient.calculatePostSignature(postPolicy);
      result.put("storeType", "oss");
      result.put("storageType", "oss");
      result.put("accessId", ossInfo.accessKeyId);
      result.put("policy", encodedPolicy);
      result.put("signature", postSignature);
      result.put("expire", String.valueOf(expireEndTime / 1000));
      result.put("dir", dir);
      result.put("host", ossInfo.endpoint.split("://")[0] + "://" + ossInfo.bucketName + "."
          + ossInfo.endpoint.split("://")[1]);
    } catch (Exception e) {
      log.error("oss 上传异常。", e);
    } finally {
      ossClient.shutdown();
    }
    return result;
  }
 
  @Override
  public void deleteFile(String pathAndName) {
    OSS ossClient = new OSSClientBuilder().build(ossInfo.endpoint, ossInfo.accessKeyId,
        ossInfo.accessKeySecret);
    try {
      ossClient.deleteObject(ossInfo.bucketName, ossInfo.rootDirectory + pathAndName);
    } finally {
      ossClient.shutdown();
    }
 
  }
 
}