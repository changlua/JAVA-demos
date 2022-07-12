package com.changlu.java;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:27 PM
 */
public class Test {

    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;
        System.out.println((num1 ^ num2) ^ num2);
        ArrayList<TreeNode> temp = new ArrayList<>();
        temp.size()
    }

    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}
