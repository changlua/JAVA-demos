package xyz.changlu.practicals.strategyAtemplate;

/**
 * @Description: 上传模式枚举类
 * @Author: changlu
 * @Date: 5:01 PM
 */
public enum UploadModeEnum {

    //注意对应的策略类的第一个字母要小写
    LOCAL("local", "fileUploadStrategyImpl"),
    OSS("oss", "ossUploadStrategyImpl");

    //配置名
    private final String mode;
    //策略类名
    private final String strategy;

    UploadModeEnum(String mode, String strategy) {
        this.mode = mode;
        this.strategy = strategy;
    }

    public String getMode() {
        return mode;
    }

    public String getStrategy() {
        return strategy;
    }

    //根据mode模式来获取到对应的上传策略全类名
    public static String getStrategy(String mode) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.mode.equals(mode)) return value.strategy;
        }
        return null;
    }
}
