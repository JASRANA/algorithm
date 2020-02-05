package n2time;

/**
 * 3、选择排序
 *
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * 是否基于交换：是
 */
public class SelectSort {
    public int[] selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minP = i;

            for (int j = i+1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minP = j;
                }
            }

            int temp = array[minP];

            array[minP] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
