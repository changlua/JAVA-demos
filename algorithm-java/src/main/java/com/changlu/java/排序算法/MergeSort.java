package com.changlu.java.排序算法;

/**
 * @ClassName MergeSort
 * @Author ChangLu
 * @Date 4/25/2022 11:43 PM
 * @Description 归并排序
 */
public class MergeSort{

    public static void main(String[] args) {
//        int[] array = {8, 4, 5, 7, 1, 3, 6, 2, 5, 5, 5};
        //800万数据 0.914s
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 800000);
        }
        long begin = System.currentTimeMillis();
        sort(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
//        System.out.println("排序后数组："+Arrays.toString(array));
        System.out.println("花费时间为:"+(double)(end-begin)/1000+"秒");
//        System.out.println(Arrays.toString(array));

        doReadFile();//读文件

    }

    public static void doReadFile() {
        System.out.println("doFile");
    }

    //空间复杂度O(n)，时间复杂度O(nlogn)
    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    //左右栏目比较=>temp=>复制到array（子操作，重复的操作）
    private static void merge(int[] array, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        //1、两个区间不断比较，将取得的小的值添加到temp中
        int[] temp = new int[right - left + 1];
        int t = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            }else {
                temp[t++] = array[j++];
            }
        }
        //处理剩余区间的值
        for(;i<=mid;){
            temp[t++] = array[i++];
        }
        for(;j<=right;){
            temp[t++] = array[j++];
        }
        //2、拷贝temp中的数到array中
//        for (int k = left; k <= right; k++) {
//            array[k] = temp[k-left];
//        }
        //更高效的拷贝方法
        System.arraycopy(temp, 0, array, left, right - left + 1);
    }
}
