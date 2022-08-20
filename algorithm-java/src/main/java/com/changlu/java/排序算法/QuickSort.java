package com.changlu.java.排序算法;

public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {8, 7, 5, 4, 8, 9, 6, 1, 2};
//        quickSort(arr,0,arr.length - 1);
//        Arrays.stream(arr).forEach((num)-> System.out.printf("%d ",num));
        Integer i = 3;
        Integer j = 2;
        System.out.println(i.compareTo(j));
        String str1 = null;
        String str2 = null;
        int i1 = str1.compareTo(str2);
    }
    
    public static void quickSort(int arr[],int l,int r){
        if (l >= r) {
            return;
        }
        //j的话指向
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < arr[l]) {
                j++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //交换j与l位置，构成基准左右两边
        int temp = arr[j];
        arr[j] = arr[l];
        arr[l] = temp;
        //进行左右区间
        quickSort(arr, j + 1, r);
        quickSort(arr, l, j - 1);
    }
}