package com.changlu.java;


import java.util.Arrays;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:27 PM
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.maxProfit(new int[]{8,9,3,5,1,3}));
    }

    public int maxProfit (int[] prices) {
        //dp[i][j] (j在0-4中)， i表示第几天。j的0-4分别表示：未买入、第一次买入、第二次卖出、第二次买入、第二次卖出
        //状态方程定义与初始化
        int n = prices.length;
        int[][] dp = new int[n][5];
        Arrays.fill(dp, -10000);//最大股票10000元
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        //可能两次交易收益最后或者一次交易最多
        return Math.max(dp[n - 1][4], Math.max(0, dp[n - 1][2]));
    }
}
