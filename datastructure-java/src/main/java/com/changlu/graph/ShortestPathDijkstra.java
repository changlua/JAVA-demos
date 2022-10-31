package com.changlu.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 最短路径
 * @Author: changlu
 * @Date: 10:10 AM
 */
public class ShortestPathDijkstra {

    //邻接矩阵
    private int[][] matrix;
    //正无穷
    private int MAX_WEIGHT = Integer.MAX_VALUE;
    //顶点集合
    private String[] vertexes;

    /**
     * 创建图2
     */
    private void createGraph() {
        matrix = new int[9][9];
        vertexes = new String[9];

        int[] v0 = { 0, 1, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
        int[] v1 = { 1, 0, 3, 7, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
        int[] v2 = { 5, 3, 0, MAX_WEIGHT, 1, 7, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
        int[] v3 = { MAX_WEIGHT, 7, MAX_WEIGHT, 0, 2, MAX_WEIGHT, 3, MAX_WEIGHT, MAX_WEIGHT };
        int[] v4 = { MAX_WEIGHT, 5, 1, 2, 0, 3, 6, 9, MAX_WEIGHT };
        int[] v5 = { MAX_WEIGHT, MAX_WEIGHT, 7, MAX_WEIGHT, 3, 0, MAX_WEIGHT, 5, MAX_WEIGHT };
        int[] v6 = { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 3, 6, MAX_WEIGHT, 0, 2, 7 };
        int[] v7 = { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 9, 5, 2, 0, 4 };
        int[] v8 = { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 7, 4, 0 };
        matrix[0] = v0;
        matrix[1] = v1;
        matrix[2] = v2;
        matrix[3] = v3;
        matrix[4] = v4;
        matrix[5] = v5;
        matrix[6] = v6;
        matrix[7] = v7;
        matrix[8] = v8;

        vertexes[0] = "v0";
        vertexes[1] = "v1";
        vertexes[2] = "v2";
        vertexes[3] = "v3";
        vertexes[4] = "v4";
        vertexes[5] = "v5";
        vertexes[6] = "v6";
        vertexes[7] = "v7";
        vertexes[8] = "v8";
    }

    /**
     * 实现dijkstra算法
     * @param vs 初始出发的顶点
     */
    public void dijkstra(int vs) {
        //三个一维数组
        //flag表示各个顶点的最短路径是否找到
        boolean[] flag = new boolean[vertexes.length];
        //U表示记录当前已知的路径之和
        int[] U = new int[vertexes.length];
        //prev前驱顶点数组
        int[] prev = new int[vertexes.length];
        //记录已求导最短路径的顶点
        String[] s = new String[vertexes.length];

        //一：初始化三个数组
        for (int i = 0; i < vertexes.length; i++) {
            flag[i] = false;//当前所有的节点都未找到最短路径
            U[i] = matrix[vs][i];//初始化当前第一个结点的所有路径
            prev[i] = 0;//由于都未找到最短路径，所以前置节点都是0
        }

        //默认第一个结点已找到最短路径（第一个节点就是开始的路径）
        flag[vs] = true;
        U[vs] = 0;
        prev[vs] = 0;
        //由于vs节点默认是最短路径，就加入到s数组中
        s[0] = vertexes[vs];

        //二：找到每个顶点的最短路径（从第1个结点开始寻找）
        //dijkstra最短路径核心代码
        int k = 0;
        for (int i = 1; i < vertexes.length; i++) {
            int min = MAX_WEIGHT;
            for (int j = 0; j < vertexes.length; j++) {
                if (flag[j] == false && U[j] < min) {
                    min = U[j];
                    k = j;
                }
            }
            //找到之后，将k点放入到s数组，该k表示已经是最短路径
            s[i] = vertexes[k];
            flag[k] = true;

            //找到从k点出发的最优路径并进行更新到U中
            for (int j = 0; j < vertexes.length; j++) {
                int tmp = matrix[k][j] == MAX_WEIGHT ? MAX_WEIGHT : (min + matrix[k][j]);
                if (flag[j] == false && tmp < U[j]) {
                    //更新路径以及前置节点
                    U[j] = tmp;
                    prev[j] = k;
                }
            }
        }

        System.out.println("起始顶点：" + vertexes[vs]);
        //打印所有顶点的最短路径
        for (int i = 0; i < vertexes.length; i++) {
            System.out.print("最短路径（" + vertexes[vs] + "," + vertexes[i] + ")：" + U[i] + " ");
            //根据prev节点来寻找路径
            List<String> path = new ArrayList<>();
            //从i点开始出发（终点）
            int j = i;
            while (true) {
                path.add(vertexes[j]);
                if (j == 0) break;
                j = prev[j];//找到上一个路径节点
            }
            //倒序输出打印
            for (int x = path.size() - 1; x >= 0; x--) {
                if (x == 0) {
                    System.out.println(path.get(x));
                }else {
                    System.out.printf(path.get(x) + "->");
                }
            }
        }
        //打印放入S中顺序
        for (int i = 0; i < vertexes.length; i++) {
            System.out.print(s[i]);
            if (i != vertexes.length - 1) System.out.print("-->");
        }

    }

    public static void main(String[] args) {
        ShortestPathDijkstra shortestPathDijkstra = new ShortestPathDijkstra();
        //初始化图
        shortestPathDijkstra.createGraph();
        shortestPathDijkstra.dijkstra(0);
    }

}
