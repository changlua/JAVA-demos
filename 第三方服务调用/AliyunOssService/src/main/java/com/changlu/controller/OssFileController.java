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