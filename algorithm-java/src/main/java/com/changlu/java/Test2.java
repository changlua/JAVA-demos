package com.changlu.java;

import javafx.util.Pair;
import java.util.*;

/**
 * @Description: 题目2
 * @Author: 茅津菁
 * @Date: 5:36 PM
 */
public class Test2 {

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        System.out.println(test2.minElements(new int[]{1, -1, 1}, 3, -4));
    }

    public int minElements(int[] nums, int limit, int goal) {
        //注意int溢出，使用sum变量
        long sum = 0;
        for (int num: nums) {
            sum += num;
        }
        //long nums1 = sum - goal;
        //向上取整
        return (int)Math.ceil(Math.abs(((long)(sum - goal)) / limit));
    }

    /**
     * 题目要求：输入任意一种物质字符串，要求输出其每种元素的数量
     * 思路：
     *   过程匹配若是匹配到大写字母(则匹配一个元素)入栈，遇到(来进行入栈，遇到)来进行栈中匹配。
     *   匹配完成之后来进行统一收集
     * @param str 物质
     * @return 每种元素的数量
     */
    public static String countStr(String str) {
        //若是匹配到数字进行累加
        //元素+数量  若是(，则默认为0
        Stack<Pair<String, Integer>> stack = new Stack<>();
        //统计总元素数量
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i ++) {
            char ch = str.charAt(i);
            //若是大写表示当前有一个元素
            if (ch >= 'A' && ch <= 'Z') {
                int j = i;
                i++;
                while (i < str.length() && (str.charAt(i) <= 'A' || str.charAt(i) >= 'Z') && !isNum(str.charAt(i)) && !isBracket(str.charAt(i))) {
                    i++;
                }
                //截取当前元素即该数量
                String element = str.substring(j, i);
                Pair<String, Integer> pair;
                if (i < str.length() && isNum(str.charAt(i))) {
                    pair = new Pair<>(element, str.charAt(i) - '0');
                }else {
                    pair = new Pair<>(element, 1);
                    i--;
                }
                stack.push(pair);
            }else if (ch == '(') {
                stack.push(new Pair<>("(", 0));
            }else if (ch == ')') {
                //来进行累加
                int size = (i + 1) < str.length() && isNum(str.charAt(i + 1)) ? str.charAt(i + 1) - '0' : 1;
                List<Pair<String, Integer>> temp = new ArrayList<>();
                //出栈
                while (stack.size() != 0 && !(stack.peek().getKey().equals("("))) {
                    Pair<String, Integer> pair = stack.pop();
                    temp.add(new Pair<>(pair.getKey(), pair.getValue() * size));
                }
                //最终碰到(
                stack.pop();
                //收集一下计算之后再统一添加到栈中
                for (int j = temp.size() - 1; j >= 0; j--) {
                    stack.push(temp.get(j));
                }
            }
        }
        //最终来进行统一收集
        List<String> keys = new ArrayList<>();
        while (!stack.isEmpty()) {
            Pair<String, Integer> element = stack.pop();
            if (!map.containsKey(element.getKey())) {
                keys.add(element.getKey());
            }
            map.put(element.getKey(), map.getOrDefault(element.getKey(), 0) + element.getValue());
        }
        //拼接字符串来进行返回
        StringBuilder builder = new StringBuilder();
        for (int i = keys.size() - 1; i >= 0; i--) {
            String key = keys.get(i);
            Integer value = map.get(key);
            builder.append(key + value);
        }
        return builder.toString();
    }

    public static boolean isNum (char ch){
        return ch >= '0' && ch <= '9';
    }

    public static boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }

}
