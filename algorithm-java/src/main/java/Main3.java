import java.util.Scanner;

/**
 * @Description:
 * @Author: changlu
 * @Date: 6:45 PM
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        if ((a & 1) == 1) {
            System.out.println("奇数");
        }else {
            System.out.println("偶数");
        }
    }
}
