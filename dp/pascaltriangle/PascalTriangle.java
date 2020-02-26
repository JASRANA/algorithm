package dp.pascaltriangle;

import java.util.Random;
import java.util.Scanner;

/**
 * 杨辉三角问题变形
 *
 * 每个位置的数字可以随意填写，经过某个数字只能到达下面一层相邻的两个数字。
 * 假设你站在第一层，往下移动，
 * 我们把移动到最底层所经过的所有数字之和，定义为路径的长度。
 * 请你编程求出从最高层移动到最底层的最短路径长度。
 */
public class PascalTriangle {
    /**
     * 动态规划
     *
     * 填表
     *   1  2  3  4  5
     * 1 5↓↘
     * 2 12 13
     * 3 14 15 17
     * 4 18 23 21 18
     * 5 20 25 30 22 23
     *
     * @param triangle 杨辉三角数组
     * @param depth 层数
     * @return 最短路径长度
     */
    public int optDp(int[][] triangle, int depth) {
        int[][] dpState = new int[depth][depth];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j <= i; j++) {
                dpState[i][j] = Integer.MAX_VALUE;
            }
        }

        // 第一个节点必选
        dpState[0][0] = triangle[0][0];
        for (int i = 1; i < depth; i++) {
            // 上一层的每一个节点都做加法
            for (int t = 0; t <= i - 1; t++) {
                // 对下一层的两个节点做加和
                // 如果该节点已有从其他节点来的路径，则取较小的那个
                dpState[i][t] = Math.min(dpState[i - 1][t] + triangle[i][t], dpState[i][t]);
                dpState[i][t + 1] = Math.min(dpState[i - 1][t] + triangle[i][t + 1], dpState[i][t + 1]);
            }
        }

        int minLength = Integer.MAX_VALUE;
        for (int j = 0; j < depth; j++) {
            if (dpState[depth - 1][j] < minLength) {
                minLength = dpState[depth - 1][j];
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入需要的杨辉三角层数：");
        int depth = input.nextInt();
        int[][] triangle = makeTriangle(depth);
        int min = new PascalTriangle().optDp(triangle, depth);
        System.out.println("最短路径为：" + min);
    }

    private static int[][] makeTriangle(int depth) {
        int[][] triangle = new int[depth][];
        Random random = new Random();
        for (int i = 0; i < depth; i++) {
            triangle[i] = new int[i + 1];
            // 第几层对应几个数字
            for (int j = 0; j <= i; j++) {
                // 随机创建
                triangle[i][j] = random.nextInt(100) + 1;
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
        return triangle;
    }
}
