package n2time;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 2、插入排序
 *
 * 时间复杂度：最好：O(n)
 *            最坏：O(n²)
 *            平均：
 * 空间复杂度：O(1)
 * 稳定性：稳定
 * 是否基于交换：是（？）
 *
 * 批改：平均也是 O(n²)。基于数组插入元素的 O(n)，循环 n 次。
 *      是基于交换的排序算法。在元素移动过程中不停交换元素。
 *
 * @author 69407
 */
public class InsertSort {
    /**
     * 本算法先查找插入位置，再对元素进行移动，多进行一次循环，效率低。
     * @param array 原数组clone
     * @return 排序后的数组
     */
    public int[] insertSortBad(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int j = 0;

            // 遍历已排序区域查找插入位置
            for (; j < i; j++) {
                if (array[i] < array[j]) {
                    break;
                }
            }

            int temp = array[i];

            // 移动元素并插入
            for (int t = i; t > j; t--) {
                array[t] = array[t-1];
            }

            array[j] = temp;
        }
        return array;
    }

    /**
     * 改进
     * @param array 原数组clone
     * @return 排序后的数组
     */
    public int[] insertSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i-1;
            for (; j >= 0; j--) {
                // 注意：这里不能用 array[i] 代替 temp，因为这个改进算法是在查找过程中就对元素进行移动。
                if (array[j] > temp) {
                    array[j+1] = array[j];
                }
                else {
                    break;
                }
            }
            array[j+1] = temp;
        }

        return array;
    }
}
