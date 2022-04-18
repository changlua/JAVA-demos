package com.changlu.java.数学问题;

/**
 * @ClassName 牛顿迭代法
 * @Author ChangLu
 * @Date 4/18/2022 8:16 PM
 * @Description 主要就是通过迭代来使得最终的值越来越精确
 */
public class 牛顿迭代法 {

    public static void main(String[] args) {
        System.out.println(sqr(0));
    }

    /**
     * 核心推导公式：X(n+1)=[X(n)+p/Xn]/2
     * @param n
     * @return
     */
    public static double sqr(double n ){
        double k = 1.0;
        while (Math.abs(k * k - n) > 1e-9) {
            k = (k + n / k) / 2;
        }
        return k;
    }

}
