//package com.changlu;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * @Description:
//        * @Author: changlu
//        * @Date: 10:19 PM
//        */
//public class Test {
//
//    private ArrayList<String> res = new ArrayList<>();
//
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        String choose = "y" ;
//        while (choose.equals("y")) {
//            System.out.println("请输入商品编号;");
//            int number = input.nextInt();
//            switch (number) {
//                case 1:
//                    System.out.println("T恤100" );
//                    break;
//                case 2:
//                    System.out.println("网球鞋200" );
//                    break;
//                case 3:
//                    System.out.println("网球拍300" );
//                    break;
//            }
//            System.out.println("是否继续（y/n): ");
//            choose = input.nextLine();
//        }
//        System. out. println("程序结束，祝您愉快!");
//    }
//
////    public int[] maxSlidingWindow(int[] nums, int k) {
////        if (nums.length < k) {
////            return new int[0];
////        }
////        List<Integer> res = new ArrayList<>();
////        //采用双端队列来解决
////        ArrayDeque<Integer> deque = new ArrayDeque<>();
////        //第一个窗口
////        for (int i = 0; i < k; i++) {
////            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
////                deque.pollLast();
////            }
////            deque.addLast(nums[i]);
////        }
////        res.add(deque.peekFirst());
////        for (int i = k; i < nums.length; i++) {
////            if (!deque.isEmpty() && deque.peekFirst() == nums[i - k]) {
////                deque.pollFirst();
////            }
////            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
////                deque.pollLast();
////            }
////            deque.addLast(nums[i]);
////            res.add(deque.peekFirst());
////        }
////        return  res.toArray(new int[]);
////    }
//}
//enum Student {
//    CHANGLU;
//}
