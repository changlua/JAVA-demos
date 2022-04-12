package com.changlu.java.O1HashAndString.exer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/2/2022 10:33 AM
 * @Description TODO
 */
public class Test {

    public static void main(String[] args) {
        HashMap<String,Integer> map =new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);

        //key封装成一个set集合即可进行遍历
        final Iterator<String> iterator = map.keySet().iterator();

        //将key、value封装到一个entry中，接着将多个entry放置到一个set里，最后使用迭代器方式来进行迭代遍历
        final Iterator<Map.Entry<String, Integer>> mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()) {
            final Map.Entry<String, Integer> next = mapIterator.next();
            System.out.println(String.format("key:%s,value:%d",next.getKey(),next.getValue()));
        }
    }

}
