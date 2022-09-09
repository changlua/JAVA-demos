package com.changlu.java;

import java.util.*;

interface TeachManager {

    boolean inputGrade(String subjectName, String userId, Integer point);

    List<Course> queryCourses(String userId);

}

class TeacherManagerImpl implements TeachManager {

    @Override
    public boolean inputGrade(String subjectName, String userId, Integer point) {
        System.out.println("inputGrade功能");
        return false;
    }

    @Override
    public List<Course> queryCourses(String userId) {
        System.out.println("queryCourses功能");
        return null;
    }
}

class Teacher {
    public static void main(String[] args) {
        String text = "LEETCODE";
        Teacher teacher = new Teacher();
        System.out.println(teacher.uniqueLetterString(text));
    }

    public int uniqueLetterString(String s) {
        //存储last字符前一个字符所在位置
        int[] lastIndexMap = new int[26];
        //存储cur字符当前所处位置
        int[] curIndexMap = new int[26];
        Arrays.fill(lastIndexMap, -1);
        Arrays.fill(curIndexMap, -1);
        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            //next字符
            int index = chars[i] - 'A';
            //cur字符的索引不是-1，计算cur字符的贡献值
            if (curIndexMap[index] > -1) {
                ans += (i - curIndexMap[index]) * (curIndexMap[index] - lastIndexMap[index]);
            }
            //滚动存放cur和last
            lastIndexMap[index] = curIndexMap[index];
            curIndexMap[index] = i;
        }
        //计算最后next字符的贡献值，最后一个位置就是s.length()
        for (int i = 0; i < 26; i++) {
            if (curIndexMap[i] > -1) {
                ans += (curIndexMap[i] - lastIndexMap[i]) * (s.length() - curIndexMap[i]);
            }
        }
        return ans;
    }
}

class StudentManageSystem {



    public static void main(String[] args) {

    }
}



class Course {
    String name;
    Date beginTime;
    Date endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

/**
 * 图邻接矩阵深度优先遍历和广度优先遍历
 */
public class GraphTest2 {
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
         *    A  B  C  D  E  F
         * A  0  1  1  1  0  0
         * B  1  0  1  0  1  0
         * C  1  1  0  1  1  0
         * D  1  0  1  0  1  1
         * E  0  1  1  1  0  0
         * F  0  0  0  1  0  0
         */
        graph.addMatrix(0, 1, 1);
        graph.addMatrix(0, 2, 1);
        graph.addMatrix(0, 3, 1);
        graph.addMatrix(1, 0, 1);
        graph.addMatrix(1, 2, 1);
        graph.addMatrix(1, 4, 1);
        graph.addMatrix(2, 0, 1);
        graph.addMatrix(2, 1, 1);
        graph.addMatrix(2, 3, 1);
        graph.addMatrix(2, 4, 1);
        graph.addMatrix(3, 0, 1);
        graph.addMatrix(3, 2, 1);
        graph.addMatrix(3, 4, 1);
        graph.addMatrix(3, 5, 1);
        graph.addMatrix(4, 1, 1);
        graph.addMatrix(4, 2, 1);
        graph.addMatrix(4, 3, 1);
        graph.addMatrix(5, 3, 1);
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

    private static Set<List<String>> set = new HashSet<>();

//    private static List<List<String>> res = new ArrayList<>();
    /**
     * 邻接矩阵深度优先遍历
     *
     * @param graph
     * @param i
     * , boolean isSupply
     */
    public void dfsAllSort(Graph graph, int i, int lastPos, List<String> list) {
        if (isVisit[i]) return;
        isVisit[i] = true;
        list.add(graph.vertex.get(i));
        boolean flag = false;
        //测试
//        List<String> asList = Arrays.asList("A", "B", "E", "D");
//        if (asList.size() == list.size()) {
//            int j = 0;
//            for (; j < asList.size(); j++) {
//                if (asList.get(j) != list.get(j)) {
//                    break;
//                }
//            }
//            if (j == asList.size()) {
//                System.out.println();
//            }
//        }
        //测试
//        System.out.print(graph.vertex.get(i) + " ");
        int[][] tmp = graph.getMatrix();
        for (int j = 0; j < graph.vertex.size(); j++) {
            if (tmp[i][j] == 1 && !isVisit[j]) {
                if (!checkHasOther(graph, i, j + 1)) {
                    dfsAllSort(graph, j, lastPos, list);
                }else {
                    dfsAllSort(graph, j, i, list);
                }
                flag = true;
                isVisit[j] = false;
                list.remove(list.size() - 1);
            }
        }
        System.out.println(list);
        //测试
//        List<String> asList1 = Arrays.asList("A", "D", "F");
//        if (asList1.size() == list.size()) {
//            int j = 0;
//            for (; j < asList1.size(); j++) {
//                if (asList1.get(j) != list.get(j)) {
//                    break;
//                }
//            }
//            if (j == asList1.size()) {
//                System.out.println();
//            }
//        }
        //测试
        if (list.size() == graph.vertex.size()) {
            //去重操作
//            System.out.println(list);
            set.add(new ArrayList<>(list));
            return;
        }
        //额外去补充 && !isSupply
        if (list.size() >= graph.vertex.size() - 3 && !flag) {
            //当前的下标是i
            for (int j = 0; j < graph.vertex.size(); j++) {
                if (tmp[lastPos][j] == 1 && !isVisit[j]) {
                    //校验之前是否已经存储相同路径
                    dfsAllSort(graph, j, i, list);
                    isVisit[j] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    private boolean checkHasOther(Graph graph, int i, int j) {
        int[][] tmp = graph.getMatrix();
        for (int k = j; k < graph.vertex.size(); k++) {
            if (tmp[i][k] == 1 && !isVisit[k]) {
                return true;
            }
        }
        return false;
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
        int n = 6;
        String[] vertexs = {"A", "B", "C", "D", "E", "F"};
        GraphTest2 graphTest = new GraphTest2();
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
        graphTest.dfsAllSort(graph, 0, 0, new ArrayList<>());
        System.out.println("========================");
        for (List<String> strings : set) {
            System.out.println(strings);
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