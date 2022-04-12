package com.changlu.PoiExcel.poi.util.uuid;

/**
 * ID生成器工具类
 *
 * @author ruoyi
 */
public class IdUtils
{

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }
}
