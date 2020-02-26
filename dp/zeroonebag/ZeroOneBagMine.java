package dp.zeroonebag;

/**
 * 0-1 背包问题（自己写的）
 *
 * 题目描述：有 n 个物品，每个物品质量不尽相同。
 *          现在需要往最大承重为 w 的背包中装这些物品，
 *          物品不能分割，只能选择装或者不装。
 *          问如何选择装法以使得背包中的质量最大
 *
 * 思路：1、动态规划递归法
 *      2、加备忘录
 */
public class ZeroOneBagMine {

    static int MAX_WEIGHT;

    int[] weight;
    /**
     * 动态规划递归写法
     * @param weight 质量数组
     * @param n 物品数量
     * @param w 背包限重
     * @return 最大可装质量
     */
    public int recDp(int[] weight, int n, int w) {
        this.weight = weight;
        MAX_WEIGHT = w;
        rec(n - 1, 0);
        return maxWeight;
    }

    /**
     * 最大质量
     */
    int maxWeight = 0;

    /**
     * 递归函数
     * @param i 正在考虑数组中第几个下标的物品
     * @param cw 现在的质量
     */
    private void rec(int i, int cw) {
        if (cw > MAX_WEIGHT) {
            return;
        }
        else {
            maxWeight = Math.max(cw, maxWeight);
        }
        if (i == -1) {
            return;
        }
        // 选
        rec(i - 1, cw + weight[i]);

        // 不选
        rec(i - 1, cw);
    }

    // ----------------------------------------------------------------------

    /**
     * 备忘录
     */
    private boolean[][] memorandum;

    /**
     * 带备忘录的递归动态规划
     *
     * @param weight 质量数组
     * @param n 物品个数
     * @param w 背包限重
     * @return 最大质量
     */
    public int recDp2(int[] weight, int n, int w) {
        memorandum = new boolean[n][w + 1];
        MAX_WEIGHT = w;
        this.weight = weight;
        rec2(n - 1, 0);
        return maxWeight;
    }

    /**
     * 递归函数
     *
     * @param i 第几个物品（从 0 开始）
     * @param cw 现在背包重量
     */
    private void rec2(int i, int cw) {
        if (cw <= MAX_WEIGHT) {
            maxWeight = Math.max(cw, maxWeight);
        }
        // 已超过最大质量，或已判断到第一个物品，或已在备忘录中
        if (cw > MAX_WEIGHT || i == -1 || memorandum[i][cw]) {
            return;
        }
        memorandum[i][cw] = true;

        // 选
        rec2(i - 1, cw + weight[i]);

        // 不选
        rec2(i - 1, cw);
    }

    // ----------------------------------------------------------------------

    /**
     * 表明物品编号-质量的状态数组
     */
    boolean[][] iCwState;

    /**
     * 动态规划非递归
     *
     * 由上一个物品选择后的状态决定下一个物品的选择
     *
     * @param weight 质量数组
     * @param n 物品个数
     * @param w 背包限重
     * @return 最大质量
     */
    public int optDp(int[] weight, int n, int w) {
        MAX_WEIGHT = w;
        iCwState = new boolean[n][w + 1];

        // 不装第一个物品，背包重量为 0 的初始状态
        iCwState[0][0] = true;

        // 装第一个物品
        if (weight[0] <= MAX_WEIGHT) {
            iCwState[0][weight[0]] = true;
        }

        // 遍历所有物品
        for (int i = 1; i < n; i++) {
            // 上一个状态的所有状态都必须推导到这一状态，保证不遗漏
            for (int j = 0; j < MAX_WEIGHT; j++) {
                if (iCwState[i - 1][j]) {
                    // 装第 i 个物品
                    // 保证装入第 i 个物品后，背包没有超重
                    if (j + weight[i] <= MAX_WEIGHT) {
                        iCwState[i][j + weight[i]] = true;
                    }
                    // 不装第 i 个物品
                    // 第 i 个物品对应的二维数组的状态与第 i - 1 个物品相同
                    //
                    // 即如果第 i - 1 个物品在背包质量为 j 的时候状态为 true，
                    // 则第 i - 1 个物品可以达到背包质量为 j 的状态
                    // 不装第 i 个物品，则第 i 个物品也可以达到质量为 j 的状态
                    iCwState[i][j] = iCwState[i - 1][j];
                }
            }
        }

        for (int j = w; j >= 0; j--) {
            // 如果最后一个物品装完之后，可以到达最大质量 j（倒序查找）
            // 则直接返回
            if (iCwState[n - 1][j]) {
                return j;
            }
        }
        return 0;
    }

    // ----------------------------------------------------------------------

    /**
     * 表明质量的状态数组
     */
    boolean[] iCwStateBetter;

    /**
     * 动态规划非递归滚动数组优化
     *
     * 只保留上一层的状态
     *
     * @param weight 质量数组
     * @param n 物品个数
     * @param w 背包限重
     * @return 最大质量
     */
    public int optDpBetter(int[] weight, int n, int w) {
        MAX_WEIGHT = w;
        // 只保存可以到达的质量
        iCwStateBetter = new boolean[w + 1];

        // 什么都不装
        iCwStateBetter[0] = true;
        for (int i = 0; i < n; i++) {
            // 每次都要遍历整个数组，以确认上一个物品装完的所有可达状态不遗漏
            //for (int j = 0; j <= w; j++) { // ！！！注意！！！如果从 0 开始，会迭代计算，前面往后面叠加
            for (int j = w; j >= 0; j--) {
                if (iCwStateBetter[j] && j + weight[i] <= MAX_WEIGHT) {
                    iCwStateBetter[j + weight[i]] = true;
                }
            }
        }
        for (int j = w; j >= 0; j--) {
            if (iCwStateBetter[j]) {
                return j;
            }
        }
        return 0;
    }
}
