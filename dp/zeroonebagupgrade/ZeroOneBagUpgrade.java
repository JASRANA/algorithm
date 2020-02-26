package dp.zeroonebagupgrade;

import dp.zeroonebag.ZeroOneBag;

import java.util.Random;
import java.util.Scanner;

/**
 * 0 - 1 背包问题升级版：引入物品价值。
 * 问如何选择物品装法，以使得在背包不超重的前提下达到最大价值
 */
public class ZeroOneBagUpgrade {

    static int MAX_WEIGHT;

    int[][] iCwState;
    /**
     * 动态规划
     *
     * @param weight 质量数组
     * @param value 价值数组
     * @param n 物品数量
     * @param w 背包限重
     * @return 最大价值
     */
    public int optDp(int[] weight, int[] value, int n, int w) {
        MAX_WEIGHT = w;
        iCwState = new int[n][w + 1];
        // 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                iCwState[i][j] = -1;
            }
        }

        // 不装第 1 个物品
        iCwState[0][0] = 0;
        if (weight[0] <= MAX_WEIGHT) {
            // 装第 1 个物品
            iCwState[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = w; j >= 0; j--) {
                // 不装，价值不变，质量不变
                if (iCwState[i - 1][j] != -1) {
                    iCwState[i][j] = iCwState[i - 1][j];
                }
                // 装
                if (iCwState[i - 1][j] != -1 && j + weight[i] <= MAX_WEIGHT) {
                    // 如果原来价值已经比装第 i 个物品要高，则还是保存原来最高价值
                    iCwState[i][j + weight[i]] = Math.max(iCwState[i - 1][j] + value[i], iCwState[i][j + weight[i]]);
                }
            }
        }

        int maxValue = 0;
        for (int j = w; j >= 0; j--) {
            if (iCwState[n - 1][j] > maxValue) {
                maxValue = iCwState[n - 1][j];
            }
        }
        return maxValue;
    }

    // ---------------------------------------------------------------

    int[] iCwStateBetter;
    /**
     * 一维数组，优化空间复杂度
     *
     * @param weight 质量数组
     * @param value 价值数组
     * @param n 物品数量
     * @param w 背包限重
     * @return 最大价值
     */
    public int optDpBetter(int[] weight, int[] value, int n, int w) {
        MAX_WEIGHT = w;
        iCwStateBetter = new int[w + 1];
        // 初始化
        for (int j = 0; j <= w; j++) {
            iCwStateBetter[j] = -1;
        }

        // 不装第 1 个物品
        iCwStateBetter[0] = 0;
        if (weight[0] <= MAX_WEIGHT) {
            // 装第 1 个物品
            iCwStateBetter[weight[0]] = value[0];
        }
        for (int i = 1; i < n; i++) {
            // for (int j = 0; j <= w; j++) { // 注意迭代问题！(不是重复计算)
            for (int j = w; j >= 0; j--) {
                // 不装的情况省略，不需要保存对应状态
                // 装
                if (iCwStateBetter[j] != -1 && j + weight[i] <= MAX_WEIGHT) {
                    // 如果原来价值已经比装第 i 个物品要高，则还是保存原来最高价值
                    iCwStateBetter[j + weight[i]] = Math.max(iCwStateBetter[j + weight[i]], iCwStateBetter[j] + value[i]);
                }
            }
        }

        int maxValue = 0;
        for (int j = w; j >= 0; j--) {
            if (iCwStateBetter[j] > maxValue) {
                maxValue = iCwStateBetter[j];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("输入物品数量：");
        int n = input.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];
        Random random = new Random();
        System.out.println("物品质量：");
        // 随机生成物品质量以及物品价值
        for (int i = 0; i < n; i++) {
            weight[i] = random.nextInt(n * 10);
            value[i] = random.nextInt(100);
            System.out.println((i + 1) + ":" + weight[i] + "，价值：" + value[i]);
        }

        int w = random.nextInt(n * 10) + n * 10;

        System.out.println("背包承重：" + w);
        int maxValue = new ZeroOneBagUpgrade().optDp(weight, value, n, w);
        System.out.println("dp —— 最大价值：" + maxValue);
        int maxValue2 = new ZeroOneBagUpgrade().optDpBetter(weight, value, n, w);
        System.out.println("dp 一维数组 —— 最大价值：" + maxValue2);
    }
}
