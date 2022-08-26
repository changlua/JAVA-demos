package com.changlu.java;

import com.changlu.java.链表.ListNode;

/**
 * @Description:
 * @Author: changlu
 * @Date: 8:22 PM
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode listNode = solution.rotateRight(node1, 2);
        System.out.println(listNode);
    }

    public ListNode rotateRight(ListNode head, int k) {
        //遍历一遍确定结点的深度，消减k
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }
        k = k % n;
        //定义虚拟结点指针
        //1 -> 2 -> 3 -> 4 -> null
        ListNode temp = new ListNode(-1, head);
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; fast != null && i < k; i++) {
            fast = fast.next;
        }
        ListNode pre = temp;
        ListNode end = null;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            if (fast.next == null) {
                end = fast;
            }
            fast = fast.next;
        }
        pre.next = null;
        end.next = head;
        return slow;
    }
}
