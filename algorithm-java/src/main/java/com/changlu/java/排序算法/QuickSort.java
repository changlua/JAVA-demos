package com.changlu.java.排序算法;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {8, 7, 5, 4, 8, 9, 6, 1, 2};
        quickSort(arr,0,arr.length - 1);
        Arrays.stream(arr).forEach((num)-> System.out.printf("%d ",num));
    }
    
    public static void quickSort(int arr[],int l,int r){
        if (l>=r)
            return;
        int mid = partition(arr,l,r);
        quickSort(arr,0,mid-1);
        quickSort(arr,mid+1,r);
    }

    public static int partition(int arr[], int l, int r){
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            //若是出现小于基准点的，那么交换到前面部分
            if (arr[i] < arr[l]){
                j++;
                swap(arr,i,j);
            }
        }
        //基准点交换
        swap(arr,j,l);
        //返回基准点
        return j;
    }

    //交换区间
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}