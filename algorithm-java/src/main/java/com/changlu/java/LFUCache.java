package com.changlu.java;

/**
    * @author Fomalhaut
    * @date 2022/3/2
    * @desc LFU缓存(2个哈希表 + N个双向链表)
    * 具体参考"王尼玛"的题解
    * 缓存的大小都是有限的，当缓存满时有新元素需要添加，
    * 就需要一种方式从缓存中删除一些元素，删除的策略就是缓存的淘汰算法。
    * LFU有个兄弟LRU，他们两都是常用的缓存淘汰算法。
    * <p>
    * LRU(Least Recently Used) 最近最少使用算法，它是根据时间维度来选择将要淘汰的元素，
    * 即删除掉最长时间没被访问的元素。
    * LFU(Least Frequently Used) 最近最不常用算法，它是根据频率维度来选择将要淘汰的元素，
    * 即删除访问频率最低的元素。
    * 如果两个元素的访问频率相同，则淘汰最久没被访问的元素。
    * 也就是说LFU淘汰的时候会选择两个维度，先比较频率，选择访问频率最小的元素；
    * 如果频率相同，则按时间维度淘汰掉最久远的那个元素。
    * <p>
    * LRU的实现是一个哈希表加上一个双链表
    * 而LFU则要复杂多了，需要用两个哈希表再加上N个双链表才能实现
    */

class LFUCache {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,0};
        System.out.println(new LFUCache().InversePairs(array));
    }

    //统计逆序对的个数
    int cnt;
    public int InversePairs(int [] array) {
        if(array.length != 0){
            divide(array,0,array.length-1);
        }
        return cnt;
    }

    //归并排序的分治---分
    private void divide(int[] arr,int start,int end){
        //递归的终止条件
        if(start >= end)
            return;
        //计算中间值，注意溢出
        int mid = start + (end - start)/2;

        //递归分
        divide(arr,start,mid);
        divide(arr,mid+1,end);

        //治
        merge(arr,start,mid,end);
    }

    private void merge(int[] arr,int start,int mid,int end){
        int[] temp = new int[end-start+1];

        //存一下变量
        int i=start,j=mid+1,k=0;
        //下面就开始两两进行比较，若前面的数大于后面的数，就构成逆序对
        while(i<=mid && j<=end){
            //若前面小于后面，直接存进去，并且移动前面数所在的数组的指针即可
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
                //a[i]>a[j]了，那么这一次，从a[i]开始到a[mid]必定都是大于这个a[j]的，因为此时分治的两边已经是各自有序了
                cnt = (cnt+mid-i+1)%1000000007;
            }
        }
        //各自还有剩余的没比完，直接赋值即可
        while(i<=mid)
            temp[k++] = arr[i++];
        while(j<=end)
            temp[k++] = arr[j++];
        //覆盖原数组
        for (k = 0; k < temp.length; k++)
            arr[start + k] = temp[k];
    }
}