package com.changlu;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Test
 * @Author ChangLu
 * @Date 4/19/2022 10:47 PM
 * @Description TODO
 */
public class Test {

    private static ConcurrentHashMap<String,Object> hashmap = new ConcurrentHashMap<>(10);

    public static void main(String[] args) {
//        System.out.println(hashmap.put("changlu", 123));
//        System.out.println(hashmap.put("changlu", 1234));//putVal(key, value, false),onlyIfAbsent=false表示覆盖
//        hashmap.putIfAbsent("changlu", 12345);//本质putVal(key, value, true)，onlyIfAbsent=true表示不覆盖
//        System.out.println(hashmap.get("changlu"));
    }
}
