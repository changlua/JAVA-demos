package xyz.changlu.practicals.strategyAtemplate;

import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Description: 本地文件上传策略
 * @Author: changlu
 * @Date: 4:57 PM
 */
@Component
public class FileUploadStrategyImpl implements UploadStrategy{
    @Override
    public String uploadFile(String fileName, InputStream inputStream) {
        System.out.println("FileUploadStrategyImpl：文本上传文件");
        return null;
    }

    @Override
    public boolean deleteFile(String path) {
        System.out.println("FileUploadStrategyImpl：删除文本文件");
        return false;
    }
}
