package com.changlu.java.字符串.leetcode;

/**
 * @ClassName 比较版本号
 * @Author ChangLu
 * @Date 4/27/2022 10:32 PM
 * @Description 比较版本号：给你两个版本号 version1 和 version2 ，请你比较它们。
 * 地址：https://leetcode-cn.com/problems/compare-version-numbers/
 */
public class 比较版本号 {

    public static void main(String[] args) {
//        System.out.println(new 比较版本号().compareVersion("1.01", "1.001"));
//        System.out.println(new 比较版本号().compareVersion("1.0", "1.0.0"));
        System.out.println(new 比较版本号().compareVersion("0.1", "1.1"));
        System.out.println(new 比较版本号().compareVersion("2.1", "1.1"));
    }

    /**
     * 方式一：双指针移动法
     * 示例：version1 = "1.01", version2 = "1.001"
     *
     * 思路：双指针来进行移动，不断的求对应的值，最后进行比较。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了30.87%的用户
     */
    public int compareVersion(String version1, String version2) {
        int v1Size= 0;
        int v2Size = 0;
        while (v1Size < version1.length() || v2Size < version2.length()) {
            int num1 = 0;
            while (v1Size < version1.length() && version1.charAt(v1Size) != '.') {
                num1 = num1 * 10 + (version1.charAt(v1Size) - '0');
                v1Size++;
            }
            int num2 = 0;
            while (v2Size < version2.length() && version2.charAt(v2Size) != '.') {
                num2 = num2 * 10 + (version2.charAt(v2Size) - '0');
                v2Size++;
            }
            if (num1 > num2) {
                return 1;
            }else if (num1 < num2) {
                return -1;
            }
            v1Size++;
            v2Size++;
        }
        return 0;
    }

}
