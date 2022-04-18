package com.changlu.java.链表.牛客网;

import com.changlu.java.链表.ListNode;

/**
 * @ClassName 链表内区间反转【中等】
 * @Author ChangLu
 * @Date 4/27/2022 6:03 PM
 * @Description 链表内区间反转：指定链表中某个区域进行反转
 * 地址：https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c?tpId=295&tqId=654&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 */
public class 链表内区间反转 {

    /**
     * 思路：起始位定位，翻转指定区间链表，需要临时保存的结点有：初始定位前置结点以及初始结点，方便反转后进行连接。
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        //到达要反转的链表节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode reverseHeadPre = null;//反转前头结点的前置结点
        ListNode reverseTail = null;//反转后链表的末尾结点
        ListNode cur = dummy;//反转后的头结点
        n = n - m + 1;
        while (m != 0) {
            if (m == 1) {
                reverseHeadPre = cur;//保存前置结点
            }
            cur = cur.next;
            m--;
        }
        //进行反转链表
        reverseTail = cur;//临时保存下链表反转后的末尾结点
        ListNode pre = null;
        while (n != 0) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            n--;
        }
        reverseTail.next = cur;
        reverseHeadPre.next = pre;
        return dummy.next;
    }

}
