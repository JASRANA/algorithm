package ntime;

/**
 * 6、桶排序
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * 稳定性：不稳定
 * 是否基于交换：否
 *
 * 适用场景：数据多，内存空间小（如对10GB订单按金额排序，但内存空间只有几百MB）
 * 注意：如果数据中包含负数，应该先转换为正数再装桶
 */
public class BucketSort {
    public int[] bucketSort(int[] array, int bucketSize) {
        if (array.length == 0) {
            return null;
        }

        // 注意：还需要取最小值，并不能默认从 0 开始
        int max = array[0];
        int min = array[1];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
            else if (value < min) {
                min = value;
            }
        }
        int bucketNums = (max - min) / bucketSize + 1;
        int[][] bucketArray = new int[bucketNums][bucketSize];
        int[] indexArray = new int[bucketNums];

        // 如果某一区间数据量大于桶容量该怎么办？ ————扩容
        // 二维数组不必每个子长度都相同
        for (int value : array) {
            int bucketIndex = (value - min) / bucketSize;
            if (bucketArray[bucketIndex].length == indexArray[bucketIndex]) {
                amplifyCapacity(bucketArray, bucketIndex);
            }
            bucketArray[bucketIndex][indexArray[bucketIndex]++] = value;
        }

        // 对每个桶进行快排合并
        int k = 0;
        for (int i = 0; i < bucketNums; i++) {
            int[] sortArray = bucketArray[i];
            quickSort(sortArray, 0, indexArray[i]-1);
            for (int j = 0; j < indexArray[i]; j++) {
                array[k++] = bucketArray[i][j];
            }
        }
        return array;
    }

    /**
     * 数组扩容
     * @param bucketArray 桶数组
     * @param bucketIndex 需要扩容的数组的下标
     */
    private void amplifyCapacity(int[][] bucketArray, int bucketIndex) {
        int[] tempArray = bucketArray[bucketIndex];
        int[] newArray = new int[tempArray.length * 2];
        for (int i = 0; i < tempArray.length; i++) {
            newArray[i] = tempArray[i];
        }
        bucketArray[bucketIndex] = newArray;
    }

    /**
     * 快排
     * @param array 排序数组
     * @param p 左边界
     * @param q 右边界
     */
    private void quickSort(int[] array, int p, int q) {
        if (p >= q) {
            return;
        }
        int r = partion(array, p, q);
        quickSort(array, p, r-1);
        quickSort(array, r+1, q);
    }

    /**
     * 分区函数
     * @param array 分区数组
     * @param p 左边界
     * @param q 有边界
     * @return 分区点下标
     */
    private int partion(int[] array, int p, int q) {
        int j = p;
        for (int i = p; i < q; i++) {
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
