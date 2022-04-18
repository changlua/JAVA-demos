package com.changlu.java.链表.leetcode;

import com.changlu.java.链表.ListNode;

import java.util.PriorityQueue;

/**
 * @ClassName 合并排序链表
 * @Author ChangLu
 * @Date 4/25/2022 6:28 PM
 * @Description 合并排序链表：给k个链表进行排序
 * 地址：https://leetcode-cn.com/problems/vvXgSW/
 */
public class 合并排序链表 {

    //方式一：比较法
    /**
     * 思路：遍历数组，每次找出最小的值连接到dummy结点上，对应的数组位置也进行改变。
     * 学习：https://www.bilibili.com/video/BV1QK4y1N7ww?spm_id_from=333.337.search-card.all.click
     *
     * 时间复杂度0(n*m)，空间复杂度O(1)
     * 执行用时：233 ms, 在所有 Java 提交中击败了5.25%的用户
     * 内存消耗：43.5 MB, 在所有 Java 提交中击败了18.49%的用户
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (true) {
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minIndex == -1) {
                    minIndex = i;
                }else{
                    //比较链表的结点，取到最小的结点
                    if (lists[i].val <= lists[minIndex].val) {
                        minIndex = i;
                    }
                }
            }
            //若是找到最小的结点位置
            if (minIndex != -1) {
                cur.next = lists[minIndex];
                cur = cur.next;
                lists[minIndex] = lists[minIndex].next;
            }
            //若是当前没有最小的结点位置了
            if (minIndex == -1) {
                return dummy.next;
            }
        }
    }

}

class 合并排序链表_Method2 {

    /**
     * 方式二：堆排序法
     * 思路：使用优先队列，首先将所有链表的首元素添加到队列中，接着以队列的元素数量作为条件来进行不断的添加与取出最小的值连接到最新的链表，最终就可以得到一个升序的链表。
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了63.11%的用户
     * 内存消耗：43 MB, 在所有 Java 提交中击败了80.38%的用户
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //优先队列（可看做是堆）
        PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        //将当前队列扔到堆中排序
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        //定义虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        //以堆中heap的数量作为条件
        while (heap.size() > 0) {
            ListNode node = heap.poll();//最小的元素
            cur.next = node;
            cur = cur.next;
            //向堆中添加新的元素
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return dummy.next;
    }

}
