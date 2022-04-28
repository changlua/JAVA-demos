package com.changlu.java.字符串.剑指offer;

/**
 * @ClassName ReplaceSpace
 * @Author ChangLu
 * @Date 4/2/2022 11:05 AM
 * @Description 05替换空格
 *   leetcode：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 */
public class 替换空格 {

    //题目：请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
    public static void main(String[] args) {
        String newStr = replaceStr("We Are Happy");
        System.out.println(newStr);
    }

    public static String replaceStr(String s){
        //1、字符串扩容，填补空缺
        StringBuilder strBuild = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                strBuild.append("  ");//填补两个空格
            }
        }
        char[] newStr = strBuild.toString().toCharArray();
        //2、定义双指针
        int left = s.length() - 1;
        int right = newStr.length - 1;
        //3、双指针同时向前进行比对填充内容
        while (left >= 0) {
            if (newStr[left] != ' '){
                newStr[right] = newStr[left];
            }else{
                newStr[right--] = '0';
                newStr[right--] = '2';
                newStr[right] = '%';
            }
            left--;
            right--;
        }
        //最终返回已填充内容
        return new String(newStr);
    }

}
