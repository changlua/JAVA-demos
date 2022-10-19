package com.changlu.java;


import java.util.Arrays;

public class Test {
    char[] s;
    int dp[][];

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.countSpecialNumbers(20));
    }

    //[1,n]之间特殊整数数目。【特殊整数的各个位置值都是互不相同的】
    public int countSpecialNumbers(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][1 << 10];
        for (int i = 0; i < m; i ++)  Arrays.fill(dp[i], -1);
        return f(0, 0, true, false);
    }

    /**
     i：指定字符数组的位数
     mask：第 i 位要选的数字不能在mask中
     isLimit：isLimit 表示当前是否受到了 n 的约束。若为真，则第 i 位填入的数字至多为 s[i]，否则可以是 9。如果在受到约束的情况下填了 s[i]，那么后续填入的数字仍会受到 nn 的约束。
     isNum：表示 i 前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为 1；若为真，则必须填数字，且要填入的数字可以从 0 开始。
     */
    public int f(int i, int mask, boolean isLimit, boolean isNum) {
        //出口
        if (i == s.length) return isNum ? 1 : 0;
        if (!isLimit && isNum && dp[i][mask] >= 0) return dp[i][mask];
        int res = 0;
        if (!isNum) res = f(i + 1, mask, false, false);//跳过当前数位
        for (int d = isNum ? 0 : 1, up = isLimit ? s[i] - '0' : 9; d <= up; ++d) {
            if ((mask >> d & 1) == 0) {
                res += f(i + 1, mask | (1 << d), isLimit && d == up, true);
            }
        }
        if (!isLimit && isNum) dp[i][mask] = res;
        return res;
    }
}