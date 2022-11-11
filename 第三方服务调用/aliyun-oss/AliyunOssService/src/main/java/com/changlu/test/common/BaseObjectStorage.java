package com.changlu.test.common;

import java.io.File;
import java.util.Map;

public abstract class BaseObjectStorage {
 
    /**
     * 上传文件
     *
     * @param pathAndName
     * @param file
     */
    public abstract void upload(String pathAndName, File file);
 
    /**
     * 授权
     *
     * @param pathAndName
     * @param time
     * @return
     */
    public abstract String authorize(String pathAndName, long time);
 
    /**
     * 授权(路径全)
     *
     * @param pathAndName
     * @param time
     * @return
     */
    public abstract String authorizeAllName(String pathAndName, long time);
 
    /**
     * 临时上传文件授权
     *
     * @param dir
     * @return
     */
    public abstract Map<String, Object> tokens(String dir);
 
    /**
     * 删除文件
     *
     * @param pathAndName
     */
    public abstract void deleteFile(String pathAndName);
 
}