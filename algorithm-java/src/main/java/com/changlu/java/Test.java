package com.changlu.java;

class Test {

    public static void main(String[] args) {
        int n = 10;
        for (int len = 1; len <= n; len ++) {
            for (int l = 1; l <= n - len + 1; l ++) {
                int r = l + len - 1;
                System.out.printf("(%d,%d) ", l, r);
            }
            System.out.println();
        }
    }

}
