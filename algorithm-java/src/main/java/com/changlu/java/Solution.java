//package com.changlu.java;
//
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
///**
// * @Description:
// * @Author: changlu
// * @Date: 8:22 PM
// */
//public class Solution {
//
//    public static int max = 0;
//
//    public static void main(String[] args)
//    }
//
//    //前导空格
//    //+-号
//    //整数>32位
//    //前导空格
//    //+-号
//    //整数>32位
//    public int myAtoi(String s) {
//        if (s == null) return 0;
//        //去除前导0
//        s = s.trim();
//        char[] arr = s.toCharArray();
//        if (s.length() == 0) {
//            return 0;
//        }
//        //判断是否是正负
//        int sign = 1;
//        int i = 1;
//        if (arr[0] == '-') {
//            sign = -1;
//        }else if (arr[0] != '+'){
//            i = 0;
//        }
//        int res = 0;
//        //-2147483648  2147483647
//        for (int j = i; j < arr.length; j++) {
//            if (arr[j] < '0' || arr[j] > '9') {
//                break;
//            }
//            //提前预判
//            if (res > 214748364 || (res == 214748364 && arr[j] >= '7')) {
//                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//            }
//            res = res * 10 + (arr[j] - '0');
//        }
//        return res * sign;
//    }
//}
//class UnionFind {
//    private int size;
//    private int[] parent;
//    private int[] weight;
//
//    public UnionFind(int[][] grid) {
//        int len1 = grid.length;
//        int len2 = grid[0].length;
//        this.size = len1 * len2;
//        this.parent = new int[size];
//        this.weight = new int[size];
//        for (int i = 0; i < len1; i++) {
//            for (int j = 0; j < len2; j++) {
//                parent[i * len1 + j] = i * len1 + j;
//                if (grid[i][j] == 1) {
//                    weight[i * len1 + j] = 1;
//                }else {
//                    weight[i * len1 + j] = 0;
//                }
//            }
//        }
//    }
//
//    public int find(int x) {
//        if (x == parent[x]) {
//            return x;
//        }else {
//            parent[x] = find(parent[x]);
//            return parent[x];
//        }
//    }
//
//    public void merge(int x, int y) {
//        int _x = find(x);
//        int _y = find(y);
//        if (_x == _y) return;
//        if (weight[_x] < weight[_y]) {
//            int temp = _x;
//            _x = _y;
//            _y = temp;
//        }
//        //连通结点
//        parent[_y] = _x;
//        weight[_x] += weight[_y];
//        Solution.max = Math.max(Solution.max, weight[_x]);
//        --size;
//    }
//}
