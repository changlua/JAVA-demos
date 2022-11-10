package com.changlu.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Node {
    int val;
    Node next;
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

class LinkedList{
    Node head;
    int size;
    
    public LinkedList() {
        //初始化头尾结点
        this.head = new Node(-1, null);
        this.size = 0;
    }
    
    //链表头部插入一个（末尾插入一个）
    public void insert(int x) {
        Node cur = head;
        for (int i = 1; i <= size; i++) {
            cur = cur.next;
        }
        cur.next = new Node(x, null);
        this.size++;
    }
    
    //在插入第k个后插入x（此操作中 k 均大于 0）
    public void insert(int k, int x) {
        Node cur = head;
        for (int i = 1; i <= k; i++) {
            cur = cur.next;
        }
        //cur为第k个元素
        Node next = cur.next;
        cur.next = new Node(x, next);
        this.size++;
    }
    
    //删除第 k 个插入的数后面的数（当 k 为 0 时，表示删除头结点）。
    public void remove(int k) {
        //删除第k+1个
        if (size == 0) return;
        Node cur = head;
        for (int i = 1; i <= k; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        this.size--;
    }
    
    public void print() {
        Node cur = head.next;
        while (cur != null) {
            System.out.printf("%d", cur.val);
            cur = cur.next;
        }
        System.out.println();
    }
    
}

public class Main {
    
    static final BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    
    public static void main (String[] args)throws Exception {
    }
}

class Test11{
    //前缀连续
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        //上下左右  分别求出二维矩阵在i,j位置的前缀连续长度
        int[][] left = new int[n + 2][n + 2], right = new int[n + 2][n + 2],
                up = new int[n + 2][n + 2], down = new int[n + 2][n + 2];
        int[][] grid = new int[n + 2][n + 2];
        for (int[] mine : mines) {
            grid[mine[0] + 1][mine[1] + 1] = 1;
        }

        //前缀初始化
        //左 -> 右   上 -> 下
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i][j] == 0) left[i][j] = left[i][j - 1] + 1;
                else left[i][j] = 0;
                if (grid[j][i] == 0) up[j][i] = up[j - 1][i] + 1;
                else up[j][i] = 0;
            }
        }
        //右 -> 左    下 -> 上
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                if (grid[i][j] == 0) right[i][j] = right[i][j + 1] + 1;
                else right[i][j] = 0;
                if (grid[j][i] == 0) down[j][i] = down[j + 1][i] + 1;
                else down[j][i] = 0;
            }
        }

        //遍历一遍矩阵
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int curPos = Math.min(left[i][j], Math.min(right[i][j], Math.min(up[i][j], down[i][j])));
                ans = Math.max(curPos, ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Test11 ss = new Test11();
        System.out.println(ss.orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
    }
}