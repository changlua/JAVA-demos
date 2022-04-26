package com.changlu.java.链表.leetcode;

/**
 * @ClassName 奇偶链表【中等】
 * @Author ChangLu
 * @Date 4/26/2022 11:01 PM
 * @Description 奇偶链表：给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * leetcode链接：https://leetcode-cn.com/problems/odd-even-linked-list/submissions/
 */
public class 奇偶链表 {

    //方式一：定义奇、偶数链表，使用导航节点来进行指引奇、偶节点进行连接，最后将偶数链表连接到奇数链表末尾返回。
    /**
     * 时间复杂度O(n)、空间复杂度O(1)
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了91.77%的用户
     */
    public ListNode oddEvenList(ListNode head) {
        //若是节点数为0或者1直接返回
        if (head == null || head.next == null) {
            return head;
        }
        //定义奇数节点、偶数节点。对于head节点不动，tail节点不断向后移动
        ListNode oddHead = head;
        ListNode oddTail = oddHead;
        ListNode evenHead = head.next;
        ListNode evenTail = evenHead;
        //定义一个导航节点
        ListNode p = head.next.next;
        while (p != null) {
            //先尝试连接奇数链表
            oddTail = oddTail.next = p;
            p = p.next;
            if (p != null) {
                //连接偶数链表
                evenTail = evenTail.next = p;
                p = p.next;
            }
        }
        //将偶数链表连接到奇数链表末节点之后
        oddTail.next = evenHead;
        evenTail.next = null;//奇数链表末尾是null
        return oddHead;
    }

}
