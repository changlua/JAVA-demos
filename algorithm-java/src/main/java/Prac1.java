//实验一 黑盒测试
import java.util.Scanner;

public class Prac1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("请输入三角形的三条边的长度：");

            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();

            if ((a + b > c) && (a + c > b) && (b + c > a)) {
                if (a == b && b == c) {
                    System.out.println("等边三角形");
                } else if ((a == b) || (b == c) || (a == c)) {
                    System.out.println("等腰三角形");
                } else {
                    System.out.println("任意三角形");
                }
            } else {
                System.out.println("输入不合法，请重新输入！！");
                continue;
            }
        }
    }
}




