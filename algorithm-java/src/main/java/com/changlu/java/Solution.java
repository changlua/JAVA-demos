package com.changlu.java;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: changlu
 * @Date: 8:22 PM
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {

    public static void main(String[] args) {
        //测试f(x)中x的一个指定范围得到的数
//        int x = 0;
//        while(x <= 25) {
//            BigInteger res = new BigInteger("1");
//            for (int i = 1; i <= x; i++) {
//                res = res.multiply(new BigInteger(String.valueOf(i)));
//            }
//            int k = (x - 1) / 5;
//            System.out.println(String.format("k = %d, x = %d, res = %d", k, x, res));
//            x++;
//        }
        Solution solution = new Solution();
        for (int i = 1; i < 32; i++) {
            //5个0，当x=25，没有一个数字（此时6个0结尾）； 29个0，x=125
            System.out.println(solution.preimageSizeFZF(i));
        }

    }

    public int preimageSizeFZF(int K) {
        //确定阶梯值范围 最终的到的K < start
        int start = 1;
        while (start < K){
            start = start*5+1;
        }

        //确定范围后，执行精确查找
        while (start > 1){
            //只有5以下阶乘才会出现start-1成立，其它情况不会存在，因为任何一个阶段分界值都会包含一个以上的5
            if(start-1 == K){
                //不存在的返回0
                return 0;
            }

            //逆推下一个阶梯值 从f(x+1) 推导出f(x)
            start=(start-1)/5;

            //获取剩余值，进行下一阶梯运算
            K%=start;
        }

        //只要存在，必然是5个
        return 5;
    }

    public void caluate() {
        BigInteger a = new BigInteger("1");
        for (int i = 2; i <= 30; i++) {
            a = a.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.print(a);
    }

}
