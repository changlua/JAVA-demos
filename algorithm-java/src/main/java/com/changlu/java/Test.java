package com.changlu.java;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.splitArraySameAverage(new int[]{1, 2, 8, 9}));
    }

    public boolean splitArraySameAverage(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        int n = nums.length, m = n / 2;
        int sum = 0;
        // 对数组求和
        for (int num : nums) {
            sum += num;
        }
        // 如果直接将 nums 中的每个元素减去数组和的平均值（sum/n），可能会引入浮点数，判断时出现误差
        // 所以这里先将 nums 的每个元素乘以 n，这样数组和的平均值则变为 sum （无需除以n），而 nums[i]*n-sum 一定为整数
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - sum;
        }

        // 将数组一分为二，从前半个数组中的 m 个元素中取出若干个元素形成不同的子集，共有 2^m 种取法，每种取法得到的子数组和用Set记录
        Set<Integer> left = new HashSet<Integer>();
        // 这里元素的选择借助位运算实现，例如对于case1的 nums=[1,2,3,4,5,6,7,8]，m=4，i取值范围为[1,15]，
        // 例如当 i=6 时，二进制表示为 110，则意味着选取第2和第3个元素求和，即 tot=nums[2]+nums[1]
        // i: 因为至少要取一个元素，所以 i 从1开始，直到 2^m-1
        for (int i = 1; i < (1 << m); i++) {
            int tot = 0;
            // 对每种取法，都要遍历前半个数组，选取相应的元素进行求和
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    tot += nums[j];
                }
            }
            // 如果前半个数组有部分元素之和为0，则剩余的所有元素之和肯定也为0，直接返回true。
            if (tot == 0) {
                return true;
            }
            left.add(tot);
        }
        // 记录后半个数组的所有元素之和，用于后面判断，避免出现同时选择数组中所有元素之和为0的情况
        int rsum = 0;
        for (int i = m; i < n; i++) {
            rsum += nums[i];
        }
        // 对后半个数组如法炮制
        for (int i = 1; i < (1 << (n - m)); i++) {
            int tot = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    tot += nums[j];
                }
            }
            // 当 后半个数组有部分元素之和为0，或者 前半个数组的子集的元素之和 是 后半个数组的子集的元素之和 的相反数时，返回true。
            // 为什么不会出现 前半个数组的真子集的元素之和 是 后半个数组的全部元素之和 的相反数这种情况？
            // 因为如果是这种情况，在遍历前半个数组时就会直接返回true
            if (tot == 0 || (rsum != tot && left.contains(-tot))) {
                return true;
            }
        }
        return false;
    }
}
