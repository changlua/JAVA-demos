package xyz.changlu.practicals.strategyAtemplate;

import java.io.InputStream;

/**
 * @Description: 上传策略
 * @Author: changlu
 * @Date: 4:51 PM
 */
public interface UploadStrategy {

    String uploadFile(String fileName, InputStream inputStream);

    boolean deleteFile(String path);

}
