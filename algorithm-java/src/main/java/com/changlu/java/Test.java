package com.changlu.java;


import java.util.Arrays;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:27 PM
 */

public class Test {

    int[] parent;
    long[] sum;

    int find(int x){
        if(x != parent[x]){
            x = find(parent[x]);
        }
        return x;
    }

    //a挂载到b上
    void union(int a, int b){
        sum[find(b)] += sum[find(a)];
        parent[find(a)] = find(b);
    }

    public static void main(String[] args) {
        Test test = new Test();
        int[] nums = {1,2,5,6,1};
        int[] removeQueries = {0,3,2,4,1};
        System.out.println(Arrays.toString(test.maximumSegmentSum(nums, removeQueries)));
    }

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries){
        int n = nums.length;
        parent = new int[n];
        sum = new long[n];
        boolean[] vis = new boolean[n];
        long[] ans = new long[n];//结果集
        for(int i = 0; i < n; i++){ //初始化操作
            parent[i] = i;
            sum[i] = nums[i];
        }
        long cur = 0;
        for(int i = n - 1; i > 0; i--){
            int r = removeQueries[i];
            vis[r] = true;
            if(r > 0 && vis[r - 1]){
                union(r - 1, r);//合并前一个元素和当前元素
            }
            if(r < n - 1 && vis[r + 1]){
                union(r, r + 1);
            }
            cur = Math.max(cur, sum[find(r)]);
            ans[i - 1] = cur;
        }
        return ans;
    }

}

