package com.changlu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 计算器
 * @Author: changlu
 * @Date: 2:58 PM
 */
class Test1{
    public static void main(String[] args) {
        //调用计算的接口方法
        System.out.println(Calculator.calculate("(1 + 5) * 4 + 3 * 5"));
    }
}

public class Calculator {

    public static void main(String[] args) {
        //测试三个转换
        test();
    }

    public static void test() {
        //中缀表达式
//        String infixExpr = "1+((2+3)*4)-5";
        String infixExpr = "(1+5)*4+3*5";
        System.out.println("中缀表达式为：" + infixExpr);
        //中缀表达式(字符串) => 中缀表达式(List集合)
        List<String> infixList = toInfixList(infixExpr);
        System.out.println("中缀表达式（List集合）：" + infixList);
        //中缀表达式(List集合) => 后缀表达式(List集合)
        List<String> suffixList = infixToSuffixExpr(infixList);
        System.out.println("后缀表达式（List集合）：" + suffixList);
        //后缀表达式(List集合) => 根据后缀表达式求值
        Integer result = calculateSuffix(suffixList);
        System.out.println("最终值：" + result);
    }

    /**
     * 对外暴露计算数学式子
     * @param expr 中缀表达式
     */
    public static int calculate(String expr) {
        //去除空字符串
        int index = 0;
        char[] chs = expr.toCharArray();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c != ' ') chs[index++] = c;
        }
        expr = new String(chs, 0, index);
        //中缀(字符串)=>中缀(集合)
        List<String> infixList = toInfixList(expr);
        //中缀表达式(List集合) => 后缀表达式(List集合)
        List<String> suffixList = infixToSuffixExpr(infixList);
        //后缀表达式(List集合) => 根据后缀表达式求值
        Integer result = calculateSuffix(suffixList);
        return result;
    }

    //后缀表达式(List集合) => 根据后缀表达式求值
    private static Integer calculateSuffix(List<String> suffixList) {
        //栈中临时存储元素值
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < suffixList.size(); i++) {
            String s = suffixList.get(i);
            if (!isOper(s)) {
                //若是数字直接存储到栈中
                stack.push(Integer.valueOf(s));
            }else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                int result = 0;
                switch (s) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        break;
                }
                stack.push(result);
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

    //中缀表达式(List集合) => 后缀表达式(List集合)
    private static List<String> infixToSuffixExpr(List<String> infixList) {
        //栈：存储符号
        Stack<String> stack = new Stack<>();
        //结果集
        List<String> result = new ArrayList<>();
        for (int i = 0; i < infixList.size(); i++) {
            String s = infixList.get(i);
            //若是当前是数字：直接添加到集合
            if (!isOper(s)) {
                result.add(s);
            }else if ("(".equals(s)){
                //若是(，直接入栈
                stack.push(s);
            }else if(")".equals(s)) {
                //若是)，匹配到栈中的(，过程中的所有符号添加到集合
                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    result.add(stack.pop());
                }
                //将(从栈中移除
                stack.pop();
            }else {
                //若是符号，首先将栈中所有权重>=当前符号的添加到集合
                while (!stack.isEmpty() && Operation.getWeight(stack.peek()) >= Operation.getWeight(s)) {
                    result.add(stack.pop());
                }
                //当前符号入栈
                stack.add(s);
            }
        }
        //若是当前栈中还有符号，全部添加到集合中
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    //中缀表达式(字符串) => 中缀表达式(List集合)
    private static List<String> toInfixList(String infixExpr) {
        char[] chs = infixExpr.toCharArray();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < chs.length;) {
            char ch = chs[i];
            //若是字符
            if (isOper(ch)) {
                result.add("" + ch);
                i++;
            }else {
                //若是数字(考虑多种情况)
                StringBuilder numStr = new StringBuilder();
                while (i < chs.length && !isOper(chs[i])) {
                    numStr.append(chs[i++]);
                }
                result.add(numStr.toString());
            }
        }
        return result;
    }

    private static boolean isOper(String str) {
        if (str.length() == 1 && (str.charAt(0) >= '0' && str.charAt(0) <= '9')) return false;
        return true;
    }

    private static boolean isOper(char ch) {
        if (ch >= '0' && ch <= '9') return false;
        return true;
    }

}

//运算符号权重
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getWeight(String expr) {
        if (expr == null) return 0;
        int val = 0;
        switch (expr) {
            case "+":
                val = ADD;
                break;
            case "-":
                val = SUB;
                break;
            case "*":
                val = MUL;
                break;
            case "/":
                val = DIV;
                break;
            default:
                break;
        }
        return val;
    }
}