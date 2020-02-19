package sort.n2time;

/**
 * 冒泡排序
 *
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 * 是否基于交换：是
 *
 * 批改：时间复杂度分最好最坏情况。
 *      最好情况：O(n)，即本身有序的情况
 *      最坏情况：O(n²)，即本身倒序的情况
 *
 * 拓展：平均情况：因为经推到可知，数组初始逆序度 = 排序交换次数，
 *               因此取最好最坏情况的平均值即 n*(n-1)/4，即O(n²)
 * @author 69407
 */
public class BubbleSort {
    public int[] bubbleSort(int[] array) {
        boolean state = true;
        while (state) {
            state = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    state = true;
                }
            }
        }
        return array;
    }
}
