package com.changlu.java;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:27 PM
 */

public class Test {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器已启动！");
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("接收到一个请求,socket:" + accept.toString());
            handle(accept);
        }
        "123".toCharArray()
    }

    public static void handle(Socket socket) {
        new Thread(() -> {
            while (true) {
                try {
                    InputStream is = socket.getInputStream();
                    byte[] data = new byte[2048];
                    int len;
                    StringBuilder builder = new StringBuilder();
                    while ((len = is.read(data)) != -1) {
                        builder.append(new String(data, 0, len));
                    }
                    System.out.println(builder.toString());
                    OutputStream os = socket.getOutputStream();
                    //写出一条数据
                    os.write("hello, word".getBytes());
                    Thread.sleep(5000);
                }catch(Exception ex){
                    ex.printStackTrace();
                    System.out.println("退出socket");
                    break;
                }
            }
        }).start();
    }

}


class Test2 {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        int[][] mat = {
                {1,0,1,0,0,0,0,0},
                {0,0,0,1,1,0,0,1},
                {0,0,1,1,1,1,1,1},
                {0,1,0,0,1,1,0,1},
                {0,1,0,0,1,0,0,0}
        };
        System.out.println(test2.maximumRows(mat, 5));
    }

    public int maximumRows(int[][] mat, int cols) {
        quanpailie(mat, cols, 0, new HashSet<>());
        return max;
    }

    private int max = -1;

    //res存储的是对应的列方案
    public void quanpailie(int[][] mat, int cols, int col, Set<Integer> res) {
        if (res.size() == cols) {
            int num = 0;
            //来进行处理业务
            for (int i = 0; i < mat.length; i++) {
                boolean flag = true;
                for (int j = 0; j < mat[0].length; j++) {
                    if (!res.contains(j) && mat[i][j] == 1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) num++;
            }
            if (num > max) max = num;
            return;
        }
        for (int i = col; i < mat[0].length; i++) {
            res.add(i);
            quanpailie(mat, cols, i + 1, res);
            res.remove(i);
        }
    }
}