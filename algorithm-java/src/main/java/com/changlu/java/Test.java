package com.changlu.java;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test {

    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("E:\\自学历程\\Gitee仓库\\demo-exer\\algorithm-java\\src\\main\\resources\\2020.txt");//注意是两个	\\
        System.out.println(fileReader);
        BufferedReader cin = new BufferedReader(fileReader);
        int k = 0;
        while (true) {
            if (k == 10 ){
                break;
            }
            String s = cin.readLine();
            System.out.println(s);
            k++;
        }
    }

}
