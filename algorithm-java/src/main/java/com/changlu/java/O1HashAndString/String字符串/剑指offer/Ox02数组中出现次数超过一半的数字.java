package com.changlu.java.O1HashAndString.String字符串.剑指offer;


/**
 * @ClassName Ox02数组中出现次数超过一半的数字
 * @Author ChangLu
 * @Date 4/6/2022 11:24 PM
 * @Description 数组中出现次数超过一半的数字
 * leetcode:https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 */
public class Ox02数组中出现次数超过一半的数字 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(new Ox02数组中出现次数超过一半的数字().majorityElement(nums));
    }


    //方式三：抵消法
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：44.7 MB, 在所有 Java 提交中击败了53.99%的用户
     */
    public int majorityElement(int[] nums) {
        int curMaxNum = 0;
        int counts = 0;
        for (int i = 0; i < nums.length; i++) {
            if (counts == 0) {
                curMaxNum = nums[i];
            }
            //核心抵消思路
            if (nums[i] == curMaxNum) {
                counts++;
            }else{
                counts--;
            }
        }
        return curMaxNum;
    }

    //方式二：排序法
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了54.68%的用户
     * 内存消耗：44.6 MB, 在所有 Java 提交中击败了68.89%的用户
     */
//    public int majorityElement(int[] nums) {
//        if (nums == null || nums.length == 0){
//            return 0;
//        }
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        Arrays.sort(nums);
//        int medium = nums.length / 2;
//        if (nums[medium] == nums[medium - 1] || nums[medium] == nums[medium + 1]){  //奇、偶数情况
//            return nums[medium];
//        }
//        return 0;
//    }

    //方式一：Map进行筛检法
    //假设数组是非空的，并且给定的数组总是存在多数元素
    /**
     * 执行用时：13 ms, 在所有 Java 提交中击败了20.01%的用户
     * 内存消耗：46.3 MB, 在所有 Java 提交中击败了16.10%的用户
     */
//    public int majorityElement(int[] nums) {
//        if (nums == null || nums.length == 0){
//            return 0;
//        }
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        Map<Integer,Integer> numsMap = new HashMap<>();
//        for (int num : nums) {
//            //判断是否有该key，有的话来进行判断当前值，没有的话进行赋值
//            Integer value = numsMap.getOrDefault(num, 0);
//            if (value == 0) {
//                numsMap.put(num, 1);
//            }else {
//                //注意：数量是大于总数的二分之一
//                if (value + 1 > nums.length / 2) {
//                    return num;
//                }
//                numsMap.put(num, value + 1);
//            }
//        }
//        return 0;
//    }

}
