package com.changlu.java.O1HashAndString.String字符串.leetcode;

/**
 * @ClassName 字符串相加
 * @Author ChangLu
 * @Date 4/18/2022 8:30 PM
 * @Description 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回
 *
 *  leetcode：https://leetcode-cn.com/problems/add-strings/   【简单】
 */
public class 字符串相加 {

    public static void main(String[] args) {
        System.out.println(new 字符串相加().addStrings("6913259244", "71103343"));
    }

    /**
     * 双指针解法：时间复杂度为O(n)
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了23.07%的用户
     */
    public String addStrings(String num1, String num2) {
        StringBuilder str = new StringBuilder("");
        int i = num1.length() - 1,j = num2.length() - 1;
        int temp = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + temp;
            temp = sum / 10;
            str.append(sum % 10);
            i--;
            j--;
        }
        if (temp == 1){
            str.append(1);
        }
        return str.reverse().toString();
    }
}
