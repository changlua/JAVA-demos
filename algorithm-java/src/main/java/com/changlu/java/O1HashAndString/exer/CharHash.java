package com.changlu.java.O1HashAndString.exer;

/**
 * @ClassName CharHash
 * @Author ChangLu
 * @Date 4/2/2022 10:07 AM
 * @Description 字符哈希：使用字符的ascii来作为哈希的下标
 */
public class CharHash {

    //题：对给定字符串中的字符进行统计
    public static void main(String[] args) {
        String str = "asdfsafdsdddfdsf";
        countAppearChat(str);
    }

    /**
     * 将字符串中的每个字符的ascii码作为统计数组的下标。由于字符数量有限可以直接使用其作为索引下标统计
     * @param str
     */
    public static void countAppearChat(String str){
        int[] chs = new int[128];
        for (int i = 0; i < str.length(); i++) {
            chs[str.charAt(i)]++;
        }
        for (int i = 0; i < 128; i++) {
            if (chs[i] > 0){
                System.out.println(String.format("字符%c出现了%d次",(char)i,chs[i]));
            }
        }
    }

}
