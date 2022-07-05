package com.changlu;

import java.util.ArrayList;

/**
 * @Description:
        * @Author: changlu
        * @Date: 10:19 PM
        */
public class Test {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        System.out.println(nums);
        ArrayList<Integer> targets = new ArrayList<>();
        targets.addAll(nums);
        nums.remove(0);
        System.out.println(nums);
        System.out.println(targets);
    }


}
enum Student {
    CHANGLU;
}