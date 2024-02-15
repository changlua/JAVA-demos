import java.util.Scanner;

public class Main6 {

    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {
        String[] arr = cin.nextLine().split(" ");
        int n = arr.length;
        //去0
        int[] a = new int[n + 1];
        int p = 0;
        for (int i = 0;i < n; i ++) {
            int num = Integer.parseInt(arr[i]);
            if (num != 0) {
                a[p ++] = num;
            }
        }
        //排序
        //Arrays.sort(a, 0, p);
        sort(a, p);
        for (int i = 0;i < p; i ++) {
            System.out.printf("%d ", a[i]);
        }
    }

    //排序
    public static void sort(int[] a, int n) {
        for (int i = 0; i < n; i ++) {
            int min = i;
            for (int j = i + 1; j < n; j ++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            //交换
            if (min != i) {
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }
}
