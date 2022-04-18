package com.changlu.java.O1HashAndString.String字符串;

/**
 * @ClassName 判断字符串组中的元素能否构成目标字符串
 * @Author ChangLu
 * @Date 4/18/2022 9:24 PM
 * @Description 大概意思就是传入一个任意字符串和一个其他字符串，判断第二个字符串可不可以拆分开，构成第一个任意字符串
 */
public class 判断字符串组中的元素能否构成目标字符串 {

    public static void main(String[] args) {
        System.out.println(new 判断字符串组中的元素能否构成目标字符串().canConstruct("aa", "aab"));
    }

    /**
     * canConstruct(“a”, “b”) -> false
     * canConstruct(“aa”, “ab”) -> false
     * canConstruct(“aa”, “aab”) -> true
     * 解题思路：字符哈希。时间复杂度O(n)。
     */
    public static boolean canConstruct(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }
        //来通过一个字符哈希来解决
        int[] charArr = new int[26];
        for (int i = 0; i < str2.length(); i++) {
            charArr[str2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str1.length(); i++) {
            charArr[str1.charAt(i) - 'a']--;
            if (charArr[str1.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
