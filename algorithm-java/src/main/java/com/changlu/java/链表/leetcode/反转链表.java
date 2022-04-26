package com.changlu.java.链表.leetcode;

/**
 * @ClassName 反转链表【简单】
 * @Author ChangLu
 * @Date 4/25/2022 3:15 PM
 * @Description 反转链表：给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 * 链接：https://leetcode-cn.com/problems/UHnkqh/submissions/
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

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
