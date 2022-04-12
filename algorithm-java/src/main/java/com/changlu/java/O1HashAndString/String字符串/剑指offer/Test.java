package com.changlu.java.O1HashAndString.String字符串.剑指offer;

/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/11/2022 12:24 AM
 * @Description 解决方案：滑动窗口(O(n))
 *
 *  713. 乘积小于K的子数组：https://leetcode-cn.com/problems/subarray-product-less-than-k/
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(new Test().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //边界，[1,1] 1=1不符合
        if (k <= 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int mult = 1;
        int count = 0;
        while (right < nums.length) {
            mult *= nums[right];
            //连续数组积不符合条件，左指针移动
            while (mult >= k) {
                mult /= nums[left];
                left++;
            }
            //累加数量
            count += right - left + 1;
            right++;
        }
        return count;
    }

    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        //同样排除k为1的情况比如  [1,1,1] k=1
        if (k <= 1) {
            return 0;
        }

        int left = 0;
        int right = 0;

        //创建一个变量记录路上的乘积
        int mul = 1;
        //记录连续数组的组合个数
        int ans = 0;

        //用右指针遍历整个数组，每次循环右指针右移一次
        while(right<nums.length) {

            //记录乘积
            mul *= nums[right];

            //当大于等于k，左指针右移并把之前左指针的数除掉
            while (mul >= k) {
                mul /= nums[left];
                left++;
            }

            //每次右指针位移到一个新位置，应该加上 x 种数组组合：
            //  nums[right]
            //  nums[right-1], nums[right]
            //  nums[right-2], nums[right-1], nums[right]
            //  nums[left], ......, nums[right-2], nums[right-1], nums[right]
            //共有 right - left + 1 种
            ans += right - left + 1;

            //右指针右移
            right++;
        }
        return ans;
    }
}
