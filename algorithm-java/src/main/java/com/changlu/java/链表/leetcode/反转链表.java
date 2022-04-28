package com.changlu.java.链表.leetcode;

import com.changlu.java.链表.ListNode;

public class 反转链表 {

    /**
     * 方式一：反转链表
     *  时间复杂度：O(n)、空间复杂度O(1)
     *  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     *  内存消耗：41.2 MB, 在所有 Java 提交中击败了35.54%的用户
     */
    public ListNode reverseList(ListNode head) {
        //边界判定
        if (head == null || head.next == null) {
            return head;
        }
        //定义前置节点
        ListNode pre = null;
        //定义后置节点：主要目的进行遍历
        ListNode cur = head;
        while (cur != null) {
            //临时保存后置节点后的内容
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

}

class Method2{
    // 方式二：采用递归写法
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了36.33%的用户
     */
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    // 递归来进行反转链表
    public ListNode reverse(ListNode pre, ListNode cur){
        if (cur == null) {
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

}
