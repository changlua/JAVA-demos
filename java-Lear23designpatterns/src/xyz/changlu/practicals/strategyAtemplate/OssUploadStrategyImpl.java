package xyz.changlu.practicals.strategyAtemplate;

import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:58 PM
 */
@Component
public class OssUploadStrategyImpl implements UploadStrategy{
    @Override
    public String uploadFile(String fileName, InputStream inputStream) {
        System.out.println("OssUploadStrategyImpl：oss上传文件");
        return null;
    }

    @Override
    public boolean deleteFile(String path) {
        System.out.println("OssUploadStrategyImpl：delete删除文件");
        return false;
    }
}
