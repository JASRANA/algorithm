package dp.zeroonebag;

/**
 * 0-1 背包问题
 *
 * 题目描述：有 n 个物品，每个物品质量不尽相同。
 *          现在需要往最大承重为 w 的背包中装这些物品，
 *          物品不能分割，只能选择装或者不装。
 *          问如何选择装法以使得背包中的质量最大
 *
 * 思路：1、回溯法 —— O(2 ^ n)
 *      2、带备忘录的回溯法 —— O(n * w)
 *      3、动态规划（二维数组 / 一维数组） —— O(n * w)
 */
public class ZeroOneBag {
    /**
     * 动态规划（二维数组）解法
     * @param weight 质量数组
     * @param n 物品个数
     * @param w 背包质量最大限制
     * @return 最大质量
     */
    public int dynamic(int[] weight, int n, int w) {
        // 状态转移数组
        boolean[][] state = new boolean[n][w + 1];
        // 初始化，背包里没有物品的情况
        state[0][0] = true;
        // 第 1 个物品装进背包
        if (weight[0] < w) {
            state[0][weight[0]] = true;
        }

        // 动态规划
        // 遍历每一个物品，分为放和不放两种情况
        // 通过上一个物品放置情况的背包质量，决定这一个物品给背包质量带来的影响
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                // 不放第 i 个物品
                if (state[i - 1][j]) {
                    state[i][j] = state[i - 1][j];
                }
            }

            for (int j = 0; j <= w - weight[i]; j++) {
                if (state[i - 1][j]) {
                    // 放第 i 个物品
                    state[i][j + weight[i]] = true;
                }
            }
        }

        int max = 0;
        // 遍历最后一个物品放置完毕后的状态数组，找出最大质量
        for (int j = w; j >= 0; j--) {
            if (state[n - 1][j]) {
                max = j;
                break;
            }
        }

        // 输出选择了哪些物品
        int mass = max;
        System.out.println("选择的物品序号为:");
        for (int i = n - 1; i >= 1; i--) {
            // 选择了第 i 件物品
            if (mass - weight[i] >= 0 && state[i - 1][mass - weight[i]]) {
                System.out.print((i + 1) + " ");
                mass -= weight[i];
            }
        }
        System.out.println();
        return max;
    }

    /**
     * 动态规划（一维数组）解法
     *
     * 减少空间复杂度的同时，不知道选择的物品序列
     *
     * @param weight 质量数组
     * @param n 物品个数
     * @param w 背包最大承重
     * @return 最大质量
     */
    public int dynamic2(int[] weight, int n, int w) {
        // 状态转移数组
        boolean[] state = new boolean[w + 1];
        // 初始化，背包里没有物品的情况
        state[0] = true;
        // 第 1 个物品装进背包
        if (weight[0] < w) {
            state[weight[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            // 将第 i 个物品装进背包
            // for (int j = 0; j <= w - weight[i]; j++) { // 每个物品都会从 0 开始重复计算
            for (int j = w - weight[i]; j >= 0; j--) {
                if (state[j]) {
                    state[j + weight[i]] = true;
                }
            }
        }

        for (int j = w; j >= 0; j--) {
            if (state[j]) {
                return j;
            }
        }
        return 0;
    }
}
