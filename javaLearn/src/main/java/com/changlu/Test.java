package com.changlu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
        * @Author: changlu
        * @Date: 10:19 PM
        */
public class Test {

    public static void main(String[] args) {
//        int[] res = new int[]{1, 3, 2, 4};
        List<Integer> res = Arrays.asList(3, 2, 4, 1);
        Collections.shuffle(res);
        System.out.println(res);
    }

//    public int[] randList(int[] res) {
//
//    }

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (!isSatisfy(s.charAt(i))) {
                i++;
            }
            while (!isSatisfy(s.charAt(j))) {
                j--;
            }
            if (i >= j) {
                break;
            }
            if (transfer(s.charAt(i++)) != transfer(s.charAt(j--))) {
                return false;
            }
        }
        return true;
    }
    public boolean isSatisfy(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            return true;
        }
        return false;
    }
    public char transfer(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch + 32);
        }
        return ch;
    }


}
enum Student {
    CHANGLU;
}