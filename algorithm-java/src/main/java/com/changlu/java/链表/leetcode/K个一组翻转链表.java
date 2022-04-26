package com.changlu.java.链表.leetcode;

import java.io.File;
import java.util.List;

/**
 * @ClassName K个一组翻转链表【困难】
 * @Author ChangLu
 * @Date 4/25/2022 4:06 PM
 * @Description K个一组翻转链表：给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */

public class K个一组翻转链表 {

    /**
     * 时间复杂度O(n)，空间复杂度O(1)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了83.91%的用户
     */
    //示例：[1,2,3,4,5] 2
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <=1 || head == null || head.next == null ) {
            return head;
        }
        ListNode dummy = new ListNode(0);//虚拟头节点
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode tail = cur;
            for (int i = 0; i < k; i++) {
                if (tail != null) {
                    tail = tail.next;
                }else {
                    return dummy.next;
                }
            }
            //找到尾节点
            pre.next = reverse(cur, tail);
            cur.next = tail;
            pre = cur;
            cur = tail;
        }
        return dummy.next;
    }

    //反转指定范围的链表
    public ListNode reverse(ListNode head,ListNode tail) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != tail) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static void main(String[] args) {
        final File file = new File("123.txt");
        System.out.println(file.getAbsoluteFile());
    }

}
