package com.changlu.tree;

import java.util.Arrays;

/**
 * @Description:
 * @Author: changlu
 * @Date: 3:43 PM
 */
public class SegmentTree {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree();
        int[] tree = new int[30];
        //测试build
        segmentTree.build(arr, tree, 0, 0, arr.length - 1);
        System.out.println(Arrays.toString(tree));
        System.out.println("搜索区间为【2,5】：");
        //测试搜索
        System.out.println(segmentTree.search(arr, tree, 0, 0, arr.length - 1, 2, 5));
        System.out.println("修改下标为3的值为4：");
        //测试修改
        segmentTree.update(arr, tree, 0, 0, arr.length - 1, 3, 4);
        System.out.print("tree数组：");
        System.out.print(Arrays.toString(tree));
        System.out.println();
        System.out.print("arr数组：");
        System.out.print(Arrays.toString(arr));
    }

    public void build(int[] arr, int[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        }else {
            int mid = (start + end) / 2;
            int leftNode = node * 2 + 1;
            int rightNode = node * 2 + 2;
            build(arr, tree, leftNode, start, mid);
            build(arr, tree, rightNode, mid + 1, end);
            tree[node] = tree[leftNode] + tree[rightNode];
        }
    }

    public int search(int[] arr, int[] tree, int node, int start, int end, int L, int R) {
        int mid = (start + end) / 2;
        int leftNode = node * 2 + 1;
        int rightNode = node * 2 + 2;
        if (R < start || L > end) {
            return 0;
        }else if (L <= start && R >= end) {
            return tree[node];
        }else if (start == end) {
            return tree[node];
        }else {
            int leftVal = search(arr, tree, leftNode, start, mid, L, R);
            int rightVal = search(arr, tree, rightNode, mid + 1, end, L, R);
            return leftVal + rightVal;
        }

    }

    public void update(int[] arr, int[] tree, int node, int start, int end, int idx, int val) {
        if (start == end) {
            arr[idx] = val;
            tree[node] = val;
        }else {
            int mid = (start + end) / 2;
            int leftNode = node * 2 + 1;
            int rightNode = node * 2 + 2;
            if (idx >= start && idx <= mid) {
                update(arr, tree, leftNode, start, mid, idx, val);
            }else {
                update(arr, tree, rightNode, mid + 1, end, idx, val);
            }
            tree[node] = tree[leftNode] + tree[rightNode];
        }
    }

}
