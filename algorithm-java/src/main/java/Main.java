import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

class Main {

    static final BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    static final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    //static final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static char[] p, s;

    public static int[] getNext(int n) {
        int[] next = new int[m];
        next[0] = 0;
        for (int i = 1, j = 0; i < n; i ++) {
            while (j > 0 && p[j] != p[i]) {
                j = next[j - 1];
            }
            if (p[j] == p[i]) {
                j ++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(cin.readLine());
        p = cin.readLine().toCharArray();
        m = Integer.parseInt(cin.readLine());
        s = cin.readLine().toCharArray();

        //将模式串来进行构造next数组
        int[] next = getNext(n);
        System.out.println(Arrays.toString(next));

        StringBuilder str = new StringBuilder();

        //进行模式匹配
        for (int i = 0, j = 0; i < m; i ++) {
            while (j > 0 && s[i] != p[j]) {
                j = next[j - 1];
            }
            if (s[i] == p[j]) j ++;
            //终止结尾
            if (j == n) {
                str.append(i - j + 1).append(" ");
                //核心点：优化重复匹配
                j = next[j - 1];
            }
        }
        //out.write(str.toString());
        out.printf(str.toString());
        out.flush();
    }

}
