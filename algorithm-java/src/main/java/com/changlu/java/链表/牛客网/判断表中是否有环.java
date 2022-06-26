package com.changlu.java.链表.牛客网;

import com.changlu.java.链表.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:37 PM
 */
public class 判断表中是否有环 {

    //题目链接：https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=295&tqId=605&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();
        while (head != null) {
            if (visited.contains(head)) {
                return true;
            }
            visited.add(head);
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] res = new int[]{1,2};
        System.out.println(Arrays.toString(Arrays.copyOfRange(res, 1, 1)));
    }

    //思路：双指针
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(num.length);
        //进行升序排序  [-10,0,10,20,-10,-40] =》[-40,-10,-10,0,10,20]
        Arrays.sort(num);
        //三个指针：i、left、right
        for (int i = 0; i < num.length - 2; i++) {
            //剪枝1：若是第一个值>0，后面的无需进行比对了
            if (num[i] > 0) {
                return result;
            }
            //剪枝2：若是前一个与当前一个相同，无需比较跳过该次
            if (i > 0 && num[i] == num[i-1]) {
                continue;
            }
            //设置left、right来开始比较
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int val = num[i] + num[left] + num[right];
                if (val > 0) {
                    right--;
                }else if (val < 0) {
                    left++;
                }else {
                    //=0情况
                    result.add(new ArrayList(Arrays.asList(num[i], num[right], num[left])));
                    //过滤掉相同情况的left、right
                    while (left < right && num[left] == num[left+1]) left++;
                    while (left < right && num[right] == num[right-1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

}
