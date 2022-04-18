package com.changlu.java.O1HashAndString.String字符串.leetcode;


/**
 * @ClassName 最小覆盖字串
 * @Author ChangLu
 * @Date 4/18/2022 9:44 PM
 * @Description 通过利用双指针来不断缩减范围来找出最小覆盖的字串。（通过字符哈希，以及左右指针进行移动来进行求解）
 *  类型：困难
 *  leetcode：https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class 最小覆盖字串 {

    public static void main(String[] args) {
        System.out.println(new 最小覆盖字串().minWindow("ABAACBAB", "ABC"));
    }

    /**
     * 滑动窗口解题：手写一遍
     *      s = "ADOBECODEBANC", t = "ABC"  ======>  ans=3表示是否范围中有了，hash表来进行索引
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.88%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了68.15%的用户
     */
    public String minWindow(String s, String t) {
        int[] hash = new int[128];
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        for (char c : charT) {
            hash[c]--;
        }
        int ans = 0;//当前范围中命中的情况
        String result = "";
        for (int right = 0,left = 0; right < charS.length; right++) {
            hash[charS[right]]++;
            //当前命中
            if (hash[charS[right]] <= 0) {
                ans++;
            }
            //范围中有命中情况
            //1、缩减窗口范围
            while (ans == t.length() && hash[charS[left]] > 0) {
                hash[charS[left++]]--;
            }
            //2、来进行置换结果集
            if (ans == t.length()) {
                if (result.equals("")  || result.length() > (right - left + 1)) {
                    result = s.substring(left, right + 1);
                }
            }
        }
        return result;
    }

    /**
     * 评论题解：
     * cnt=t.len表示当前区域是否已经命中
     * hash[]数组中每个槽>0则表示当前范围中有过剩的可以缩减范围；
     */
//    public String minWindow(String s, String t) {
//        char[] chars = s.toCharArray(), chart = t.toCharArray();
//        int n = chars.length, m = chart.length;
//
//        int[] hash = new int[128];
//        for (char ch : chart) hash[ch]--;
//
//        String res = "";
//        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
//            hash[chars[i]]++;
//            if (hash[chars[i]] <= 0) cnt++;  //cnt表示命中的数量
//            while (cnt == m && hash[chars[j]] > 0)   //若是当前已经全部命中 & 此时j的位置
//                hash[chars[j++]]--;
//            if (cnt == m)
//                if (res.equals("") || res.length() > i - j + 1)
//                    res = s.substring(j, i + 1);
//        }
//        return res;
//    }

}
