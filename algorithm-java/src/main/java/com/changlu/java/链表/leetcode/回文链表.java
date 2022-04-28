package com.changlu.java.链表.leetcode;

import com.changlu.java.链表.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 回文链表【简单】
 * @Author ChangLu
 * @Date 4/26/2022 6:07 PM
 * @Description 回文链表：给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 地址：https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class 回文链表 {
    //方式一：全部添加到集合中，然后依次比较
    /**
     * 时间复杂度O(n)，空间复杂度O(n)
     * 执行用时：6 ms, 在所有 Java 提交中击败了51.96%的用户
     * 内存消耗：53.6 MB, 在所有 Java 提交中击败了70.35%的用户
     */
    public boolean isPalindrome(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        //来进行依次比对
        int left = 0;
        int right = nodes.size() - 1;
        while (left < right) {
            if (nodes.get(left).val != nodes.get(right).val) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

class 回文链表_Method2{

    //方式二：快慢指针+链表反转，最终进行回文比对
    /**
     * 时间复杂度O(n),空间复杂度O(1)
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.10%的用户
     * 内存消耗：57.4 MB, 在所有 Java 提交中击败了49.65%的用户
     */
    public boolean isPalindrome(ListNode head) {
        //定义快慢指针
        ListNode slow = head;
        ListNode fast = head;
        ListNode cur = slow;//根据慢指针来进行不断反转
        ListNode pre = null;
        //在慢指针移动时顺便反转一半链表
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            cur.next = pre;
            pre = cur;
            cur = slow;
        }
        //奇数情况
        if (fast != null) {
            slow = slow.next;
        }

        //最后进行对比比较值：pre与slow
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }


}
