package com.changlu.集合注意事项;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 集合转数组
 * @Author: changlu
 * @Date: 7:32 PM
 */
public class Gather2Arrary {
    public static void main(String[] args) {
        String [] s= new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        //直接调用空参的toArray()：返回的是一个Object对象
        Object[] objects = list.toArray();
        //传入一个0个大小的字符串数组：new String[0]就是起一个模板的作用，指定了返回数组的类型，0 是为了节省空间，因为它只是为了说明返回的类型。
        String[] strings = list.toArray(new String[0]);
    }
}
