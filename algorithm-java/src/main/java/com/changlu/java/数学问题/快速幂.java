package com.changlu.java.数学问题;

/**
 * @ClassName 快速幂
 * @Author ChangLu
 * @Date 4/18/2022 7:28 PM
 * @Description 快速幂求解
 */
public class 快速幂 {

    //取模操作(对大素数取模)
    private static final long MOD = 1000000007;

    public static void main(String[] args) {
//        System.out.println(qpow(2, 3));
        System.out.println(qpow2(2, 100000000));
    }

    /**
     * 递归快速幂
     * @param a 实数a
     * @param n 阶数n，三种情况，n=0,n=奇数，n=偶数
     * @return
     */
    public static long qpow(long a, long n){
        if (n == 0){
            return 1;
        }else if ((n & 1) == 1) {  //奇数
            return qpow(a, n -1) * a % MOD;
        }else {
            long temp = qpow(a, n / 2) % MOD;
            return temp * temp % MOD;
        }
    }

    /**
     * 非递归方式
     */
    public static long qpow2(long a, long n) {
        long ans = 1;
        while ( n != 0) {
            if ((n & 1) == 1) { //若是n为奇数
                ans *= a % MOD;
                ans %= MOD;//求模处理
            }
            a *= a % MOD; //这个就表示偶数情况
            a = a % MOD;//求模处理
            n = n >> 1;
        }
        return ans;
    }


}
