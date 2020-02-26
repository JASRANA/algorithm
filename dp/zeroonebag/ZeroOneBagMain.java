package dp.zeroonebag;

import java.util.Random;
import java.util.Scanner;

public class ZeroOneBagMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("输入物品数量：");
        int n = input.nextInt();

        int[] weight = new int[n];
        Random random = new Random();
        System.out.println("物品质量：");
        // 随机生成物品质量
        for (int i = 0; i < n; i++) {
            weight[i] = random.nextInt(n * 10);
            System.out.println((i + 1) + ":" + weight[i]);
        }

        int w = random.nextInt(n * 100) + n;

        System.out.println("背包承重：" + w);
        int max1 = new ZeroOneBag().dynamic(weight, n, w);
        System.out.println("官方二维数组 —— 最大质量：" + max1);
        int max2 = new ZeroOneBag().dynamic2(weight, n, w);
        System.out.println("官方一维数组 —— 最大质量：" + max2);
//        int max3 = new ZeroOneBagMine().recDp(weight, n, w);
//        System.out.println("我的 dp 递归：最大质量：" + max3);
        int max4 = new ZeroOneBagMine().recDp2(weight, n, w);
        System.out.println("我的 dp 递归带备忘录 —— 最大质量：" + max4);
        int max5 = new ZeroOneBagMine().optDp(weight, n, w);
        System.out.println("我的 dp 非递归 —— 最大质量：" + max5);
        int max6 = new ZeroOneBagMine().optDpBetter(weight, n, w);
        System.out.println("我的 dp 非递归滚动数组 —— 最大质量：" + max6);
    }
}
