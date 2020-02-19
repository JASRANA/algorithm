package sort.nlogntime;

/**
 * 5、快速排序
 *
 * 时间复杂度：最优平均：O(nlogn)
 *            极端：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * 是否基于交换：是
 */
public class QuickSort {
    public int[] quickSort(int[] array) {
        sortRecursion(array, 0, array.length-1);
        return array;
    }

    private void sortRecursion(int[] array, int p, int q) {
           if (p >= q) {
            return;
        }
        int r = partition(array, p, q);
        sortRecursion(array, p, r-1);
        sortRecursion(array, r+1, q);
    }

    private int partition(int[] array, int p, int q) {
        int j = p;
        for (int i = p; i < q; i++) {
            // 这里直接选取最后一个元素作为分区点
            if (array[i] < array[q]) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j++;
            }
        }

        int temp = array[j];
        array[j] = array[q];
        array[q] = temp;

        return j;
    }
}
