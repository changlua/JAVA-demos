package com.changlu.springbootdemo.utils;

/**
 * @ClassName StringUtils
 * @Author ChangLu
 * @Date 2021/9/25 17:10
 * @Description TODO
 */
public class StringUtils {

    //空字符串
    private static final String EMPTY_STR = "";

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return isNull(str) || EMPTY_STR.equals(str.trim());
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isNull(Object object){
        return  object == null;
    }

    public static void main(String[] args) {
        //1、测试是字符串是否为空
        System.out.println(StringUtils.isEmpty("  "));
    }
}