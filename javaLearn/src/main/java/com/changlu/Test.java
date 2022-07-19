package com.changlu;

import java.util.Arrays;

/**
 * @Description:
        * @Author: changlu
        * @Date: 10:19 PM
        */
public class Test {

    public static void main(String[] args) {
        String str = "20EE:FGb8:85a3:0:0:8A2E:0370:7334";
        char[] chars = str.toCharArray();
        chars[5] = 5 + '0';
        System.out.println(Arrays.toString(chars));
    }


}
enum Student {
    CHANGLU;
}