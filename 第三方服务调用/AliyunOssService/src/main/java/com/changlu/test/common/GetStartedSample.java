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
//             * Determine whether the bucket exists
//             */
//            if (!ossClient.doesBucketExist(bucketName)) {
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