package com.changlu.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图邻接矩阵深度优先遍历和广度优先遍历
 */
public class GraphTest {
    public boolean[] isVisit;           //是否被访问过，深度优先
    public boolean[] bfsVisit;          //是否被访问过,广度优先

    public class Graph {
        public List<String> vertex;     //顶点集合
        public int[][] matrix;          //邻接矩阵集合
        public int edge;                //边数

        /**
         * 构造
         *
         * @param n 顶点数
         */
        public Graph(int n) {
            vertex = new ArrayList<String>(n);
            matrix = new int[n][n];
            isVisit = new boolean[n];
            bfsVisit = new boolean[n];
        }

        /**
         * 添加节点
         *
         * @param v 节点名称
         */
        public void addVertex(String v) {
            vertex.add(v);
        }

        /**
         * 构建邻接矩阵数组
         *
         * @param i
         * @param j
         * @param val
         */
        public void addMatrix(int i, int j, int val) {
            this.matrix[i][j] = val;
            this.matrix[j][i] = val;
        }

        public int[][] getMatrix() {
            return this.matrix;
        }
    }

    /**
     * 创建图
     *
     * @param n
     * @return
     */
    public Graph createGraph(int n) {
        return new Graph(n);
    }

    /**
     * 邻接矩阵顺序存储
     *
     * @param graph
     */
    public void createMatrix(Graph graph) {
        /**
         *    V1 V2 V3 V4 V5 V6 V7 V8
         * V1 0  1  1  0  0  0  0  0
         * V2 1  0  0  1  1  0  0  0
         * V3 1  0  0  0  0  1  1  0
         * V4 0  1  0  0  0  0  0  1
         * V5 0  1  0  0  0  0  0  1
         * V6 0  0  1  0  0  0  1  0
         * V7 0  0  1  0  0  1  0  0
         * V8 0  0  0  1  1  0  0  0
         */
        graph.addMatrix(0, 1, 1);
        graph.addMatrix(0, 2, 1);
        graph.addMatrix(1, 0, 1);
        graph.addMatrix(1, 3, 1);
        graph.addMatrix(1, 4, 1);
        graph.addMatrix(2, 0, 1);
        graph.addMatrix(2, 5, 1);
        graph.addMatrix(2, 6, 1);
        graph.addMatrix(3, 1, 1);
        graph.addMatrix(3, 7, 1);
        graph.addMatrix(4, 1, 1);
        graph.addMatrix(4, 7, 1);
        graph.addMatrix(5, 2, 1);
        graph.addMatrix(5, 6, 1);
        graph.addMatrix(6, 2, 1);
        graph.addMatrix(6, 5, 1);
        graph.addMatrix(7, 3, 1);
        graph.addMatrix(7, 4, 1);
    }

    /**
     * 邻接矩阵深度优先遍历
     *
     * @param graph
     * @param i
     */
    public void dfsSort(Graph graph, int i) {
        if (isVisit[i]) return;
        isVisit[i] = true;
        System.out.print(graph.vertex.get(i) + " ");
        int[][] tmp = graph.getMatrix();
        for (int j = 0; j < graph.vertex.size(); j++) {
            if (tmp[i][j] == 1 && !isVisit[j]) dfsSort(graph, j);
        }
        //回溯
        isVisit[i] = false;
    }

    private static List<List<String>> res = new ArrayList<>();

    /**
     * 邻接矩阵深度优先遍历
     *
     * @param graph
     * @param i
     */
    public void dfsAllSort(Graph graph, int i, List<String> list) {
        if (isVisit[i]) return;
        isVisit[i] = true;
        list.add(graph.vertex.get(i));
        if (list.size() == graph.vertex.size()) {
            res.add(list);
            return;
        }
//        System.out.print(graph.vertex.get(i) + " ");
        int[][] tmp = graph.getMatrix();
        for (int j = 0; j < graph.vertex.size(); j++) {
            if (tmp[i][j] == 1 && !isVisit[j]) dfsAllSort(graph, j, list);
        }
        //回溯
        isVisit[i] = false;
        list.remove(list.size() - 1);
    }

    /**
     * 邻接矩阵广度优先遍历
     *
     * @param graph
     */
    public void bfsSort(Graph graph, int i) {
        if (bfsVisit[i]) return;
        bfsVisit[i] = true;
        System.out.print(graph.vertex.get(i) + " ");
        Queue<Integer> queue = new LinkedList();
        queue.add(i);
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            for (int j = 0; j < graph.vertex.size(); j++) {
                if (graph.getMatrix()[idx][j] == 1 && !bfsVisit[j]) {
                    bfsVisit[j] = true;
                    System.out.print(graph.vertex.get(j) + " ");
                    queue.add(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 8;
        String[] vertexs = {"V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8"};
        GraphTest graphTest = new GraphTest();
        Graph graph = graphTest.createGraph(n);
        for (int i = 0; i < vertexs.length; i++) {
            graph.addVertex(vertexs[i].toString());
        }
        graphTest.createMatrix(graph);
        /*int[][] data = graph.getMatrix();
        //System.out.println(Arrays.deepToString(data));
        //测试邻接矩阵二维数据
        for (int[] tmp : data) {
            for (int x : tmp) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
        */
        System.out.println("邻接矩阵深度优先遍历");
        List<String> vertexList = graph.vertex;
        graphTest.dfsAllSort(graph, 0, new ArrayList<>());
        for (List<String> re : res) {
            System.out.println(res);
        }
//        for (int i = 0; i < vertexList.size(); i++) {
//            graphTest.dfsSort(graph, i);
//        }
//        System.out.println("");
//        System.out.println("邻接矩阵广度优先遍历");
//        for (int i = 0; i < vertexList.size(); i++) {
//            graphTest.bfsSort(graph, i);
//        }
    }
}