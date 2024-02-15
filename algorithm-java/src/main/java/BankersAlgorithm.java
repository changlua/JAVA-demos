import java.util.Arrays;

public class BankersAlgorithm {

    // 定义常量
    private static final int NUM_PROCESSES = 5; // 进程数
    private static final int NUM_RESOURCES = 3; // 资源数

    private int[][] max; // 最大需求矩阵
    private int[][] allocation; // 已分配矩阵
    private int[][] need; // 需求矩阵
    private int[] available; // 系统可用资源向量

    public BankersAlgorithm() {
        // 初始化各矩阵和向量
        max = new int[NUM_PROCESSES][NUM_RESOURCES];
        allocation = new int[NUM_PROCESSES][NUM_RESOURCES];
        need = new int[NUM_PROCESSES][NUM_RESOURCES];
        available = new int[NUM_RESOURCES];
    }

    // 设置最大需求矩阵
    public void setMax(int[][] max) {
        this.max = max;
    }

    // 设置已分配矩阵
    public void setAllocation(int[][] allocation) {
        this.allocation = allocation;
    }

    // 设置系统可用资源向量
    public void setAvailable(int[] available) {
        this.available = available;
    }

    // 计算需求矩阵
    private void calculateNeedMatrix() {
        for (int i = 0; i < NUM_PROCESSES; i++) {
            for (int j = 0; j < NUM_RESOURCES; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    // 检查进程是否可以安全执行
    public boolean isSafe() {
        calculateNeedMatrix();

        boolean[] finish = new boolean[NUM_PROCESSES];
        int[] work = Arrays.copyOf(available, NUM_RESOURCES);//复制可用空间
        int[] safeSequence = new int[NUM_PROCESSES];// 安全圈
        int count = 0;

        while (count < NUM_PROCESSES) { //根据安全圈的中的数是否为进程数作为判断条件
            boolean found = false;
            for (int i = 0; i < NUM_PROCESSES; i++) {  //遍历每一个进程
                if (!finish[i]) { //是否已经分配
                    boolean canExecute = true;
                    for (int j = 0; j < NUM_RESOURCES; j++) {  //遍历当前进程中的每个资源
                        if (need[i][j] > work[j]) {
                            canExecute = false;
                            break;
                        }
                    }
                    if (canExecute) {
                        // 进程可以安全执行
                        for (int j = 0; j < NUM_RESOURCES; j++) {
                            work[j] += allocation[i][j];
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                break;
            }
        }

        if (count == NUM_PROCESSES) {
            // 存在安全序列
            System.out.println("安全序列：");
            for (int i = 0; i < NUM_PROCESSES; i++) {
                System.out.print("P" + safeSequence[i] + " ");
            }
            System.out.println();
            return true;
        } else {
            // 不存在安全序列
            System.out.println("没有安全序列！");
            return false;
        }
    }

    public static void main(String[] args) {
        BankersAlgorithm bankersAlgorithm = new BankersAlgorithm();

        // 设置最大需求矩阵
        int[][] max = {
                {7, 5, 3},
                {3, 2, 2},
                {9, 0, 2},
                {2, 2, 2},
                {4, 3, 3}
        };
        bankersAlgorithm.setMax(max);

        // 设置已分配矩阵
        int[][] allocation = {
                {0, 1, 0},
                {2, 0, 0},
                {3, 0, 2},
                {2, 1, 1},
                {0, 0, 2}
        };
        bankersAlgorithm.setAllocation(allocation);

        // 设置系统可用资源向量
        int[] available = {3, 3, 2};
        bankersAlgorithm.setAvailable(available);

        bankersAlgorithm.isSafe();
    }
}
