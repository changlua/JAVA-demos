package com.changlu.ClassDemo;


/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/19/2022 9:37 AM
 * @Description TODO
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new Test().test());
    }

    public int test(){
        try {
            int i = 1/0;
        }catch (Exception e) {
            return 1;
        }finally {
            return 0;
        }
    }
}
