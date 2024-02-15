package com.changlu.java;

class Solution {

    /**
     * 辗转相减法（指数的最大公约数）
     *
     * @return
     */
    public static int gcd_sub(int a, int b) {
        if (b == 1) return a;
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        return gcd_sub(b, a / b);
    }

    public static int gcd_sub2(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(gcd_sub(25, 125));
        System.out.println(gcd_sub2(25, 125));
    }
}
