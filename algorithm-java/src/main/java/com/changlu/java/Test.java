package com.changlu.java;

public class Test {
    private int[][] dicts = {
            {0, -1},
            {-1, 0},
            {0, 1},
            {1, 0}
    };

    public static void main(String[] args) {
        Test test = new Test();
        int[][] grid = {
                {1, 1},
                {1, 1}
        };
        System.out.println(test.islandPerimeter(grid));
    }

    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return dfs(i, j, grid, new boolean[n][m]);
                }
            }
        }
        return 0;
    }

    public int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        System.out.printf("%d, %d\n", i, j);
        //边界情况
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return 1;
        //若是走不通，直接返回1
        if (grid[i][j] == 0) return 1;
        //若是当前访问过了，直接返回
        if (visited[i][j]) return 0;
        visited[i][j] = true;
        int res = 0;
        //四个方向
        for (int d = 0; d < 4; d++) {
            int x = i + dicts[d][0];
            int y = j + dicts[d][1];
            //System.out.printf("%d, %d\n", x, y);
            res += dfs (x, y, grid, visited);
        }
        return res;
    }

}
