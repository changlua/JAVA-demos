package com.changlu.java;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(new int[]{1, 1, 2}));
    }

    //结果集
    private ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        recursion(nums, list, new boolean[nums.length]);
        return res;
    }

    public void recursion(int[] nums, ArrayList<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //i > 0 && nums[i] == nums[i - 1] && !visited[i]处理的是递归下去情况中出现重复的排列情况
            //举例：112   第一轮：112 121  第二轮的第一个数字，此时为1，visited[1]为false，此时重点来了看后面的条件，若是当前值与之前的相同并且前面的此时并没有访问过，那么跳过这个情况
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            recursion(nums, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}