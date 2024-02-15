import java.util.Scanner;

/**
 * @Description:
 * @Author: changlu
 * @Date: 6:47 PM
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int max = Math.max(a, b);
        max = Math.max(max, c);
        System.out.println("最大的数为：" + max);
    }
}
