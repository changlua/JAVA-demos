package com.changlu.java.链表.leetcode;

import com.changlu.java.链表.ListNode;

/**
 * @ClassName 合并两个有序链表 【简单】
 * @Author ChangLu
 * @Date 4/25/2022 5:13 PM
 * @Description 合并两个有序链表：将两个有序链表合并成一个链表
 * 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 */
public class 合并两个有序链表 {

    /**
     * 方式一：两两比较
     * 思路：定义一个新节点，并且设置虚拟头结点，接着对两个链表进行依次比较连接。
     * 时间复杂度O(n)，空间复杂度O(n)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了63.20%的用户
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        //处理链表剩余节点，只要有一方为空，直接连接另一方即可
        if (l1 == null) {
            cur.next = l2;
        }
        if (l2 == null) {
            cur.next = l1;
        }
        return dummy.next;
    }

}

class 合并两个有序链表_Method2{

    //方式二：递归解法
    /**
     * 思路：每个子问题都是比较两个值得大小，最终返回一个已经排好序的链表
     *
     * 时间复杂度O(n)，空间复杂度O(1)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了37.67%的用户
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }else if (l2 == null) {
            return l1;
        }else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
