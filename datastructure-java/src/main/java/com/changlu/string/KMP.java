package com.changlu.string;

/**
 * @Description:
 * @Author: changlu
 * @Date: 5:13 PM
 */
public class KMP {

    //26个字母
    //1-1000
    int[] arr = new int[26];

    public static void main(String[] args) {
        //API
        System.out.println("ababcabcaabbcdeabcdef".indexOf("abcaabb"));
        //手写
        System.out.println(kmp("ababcabcaabbcdeabcdef", "abcaabb"));
        //

    }

    public static int kmp (String str, String sub) {
        int[] next = getNext(sub);
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != sub.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == sub.charAt(j)) j++;
            //若是匹配到最后
            if (j == sub.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] getNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i ++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            //若是当前字符相等
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
