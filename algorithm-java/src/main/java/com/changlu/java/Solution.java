package com.changlu.java;


import java.util.*;

/**
 * @Description:
 * @Author: changlu
 * @Date: 8:22 PM
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    private static int n;
    private static boolean[] v = new boolean[10];
    //记录结果集
    private static int ans;

    //检查
    private static boolean check(int a, int c) {
        //计算b
        int b = n * c - a * c;

        //提前剪枝（若是出现0则直接结束）
        if (a == 0 || b <= 0 || c == 0) return false;

        //复制拷贝是否存在的数组（避免重新恢复现场）
        boolean[] vv = Arrays.copyOfRange(v, 0, v.length);
        //遍历b的所有元素
        while (b != 0) {
            int x = b % 10;
            //若是b中出现0或者之前已经出现过，此时直接就结束
            try {
                if (x == 0 || vv[x]) return false;
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            //记录访问过
            vv[x] = true;
            b = b / 10;
        }
        //遍历10个数是否都有存在
        for (int i = 1; i <= 9; i++) {
            if (!vv[i]) return false;
        }
        return true;
    }

    public static void dfs_c(int u, int a, int c) {
        //c以n的最大长度最为基准
        if (u == n) return;
        //相当于枚举出来一组a,c
        if (check(a, c)) ans++;

        //向下递归推举出下一个c
        for (int i = 1; i <= 9; i++) {
            if (!v[i]) {
                v[i] = true;
                dfs_c(u + 1, a, c * 10 + i);
                v[i] = false;
            }
        }
    }

    //在进行递归推的时候算出a的值
    public static void dfs_a(int u, int a) {
        //若是a的值>=n数字，此时就直接提前结束
        if (a >= n) return;
        //符合条件情况，向下递归推出c
        if (a != 0) {
            dfs_c(u, a, 0);
        }

        for (int i = 1; i <= 9; i++) {
            if (!v[i]) {
                v[i] = true;
                dfs_a(u + 1, a * 10 + i);
                v[i] = false;
            }
        }
    }

    //枚举出来a,c => 推出b，最后进行check检查
    //全排列+check检查【更加复杂】
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dfs_a(0, 0);
        System.out.printf("%d", ans);
    }

}
