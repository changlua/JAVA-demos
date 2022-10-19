package xyz.changlu.practicals.strategyAtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:51 PM
 */
@Component
public class UploadStrategyContext {

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

    //可从文件中来进行读取
    private String mode = "local";

    /**
     * 选择指定的模式来进行上传服务
     * @return
     */
    public String executeUploadStrategy(String fileName, InputStream inputStream) {
        String strategyClassName = UploadModeEnum.getStrategy(mode);
        return uploadStrategyMap.get(strategyClassName).uploadFile(fileName, inputStream);
    }

    /**
     * 选择指定的模式来进行上传服务
     * mode 自定义模式（根据对应的配置项即可）
     * @return
     */
    public String executeUploadStrategy(String mode, String fileName, InputStream inputStream) {
        String strategyClassName = UploadModeEnum.getStrategy(mode);
        return uploadStrategyMap.get(strategyClassName).uploadFile(fileName, inputStream);
    }

    /**
     * 选择指定的模式来进行上传服务
     * @return
     */
    public boolean executeDeleteStrategy(String path) {
        String strategyClassName = UploadModeEnum.getStrategy(mode);
        return uploadStrategyMap.get(strategyClassName).deleteFile(path);
    }

    /**
     * 选择指定的模式来进行上传服务
     * @param mode
     * @return
     */
    public boolean executeDeleteStrategy(String mode, String path) {
        String strategyClassName = UploadModeEnum.getStrategy(mode);
        return uploadStrategyMap.get(strategyClassName).deleteFile(path);
    }

}
