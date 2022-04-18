package com.changlu.java.O1HashAndString.String字符串.剑指offer;

import java.util.Arrays;

/**
 * @ClassName Ox03最小的K个数
 * @Author ChangLu
 * @Date 4/7/2022 11:49 PM
 * @Description 最小的K个数
 * leetcode：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 */
public class 最小的K个数 {

    public static void main(String[] args) {
        int[] arr = new int[]{4,5,1,6,2,7,3,8};
        System.out.println(Arrays.toString(new 最小的K个数().getLeastNumbers(arr, 4)));
    }

    /**
     *  执行用时：6 ms, 在所有 Java 提交中击败了72.26%的用户
     *  内存消耗：42.5 MB, 在所有 Java 提交中击败了21.53%的用户
     */
    //1、API调用排序法
    //输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length <= k){
            return arr;
        }
        //1、排序
        // Arrays.sort(int[]) 使用双轴快速排序算法,时间复杂度为0(logn)
        // Collections.sort(List) 是一种优化过的合并排序算法,时间复杂度是O(n)
        Arrays.sort(arr);
        //2、取出前k个数（优化：使用API取）
//        int[] numArr = new int[k];
//        for (int i = 0; i < k; i++) {
//            numArr[i] = arr[i];
//        }
//        return numArr;
        return Arrays.copyOfRange(arr, 0, k);//[0，4)
    }
}
