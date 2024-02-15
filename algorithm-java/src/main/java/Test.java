
public class Test {
    public static void main(String[] args) {
        String s = new String("helloworld");
        System.out.println(new String(s.toCharArray(), 2, 3));
    }
}
