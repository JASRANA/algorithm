package ntime;

/**
 * 7、计数排序
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * 稳定性：不稳定
 * 是否基于交换：否
 *
 * 适用场景：数据范围小，数据多（如高考成绩考生名次排序）
 * 注意：如果数据中包含负数，应该先转换为正数再装桶
 */
public class CountingSort {

    private static final int COUNTING_BUCKET_SIZE = 1;

    public int[] countingSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int min = array[0];
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
            else if (value < min) {
                min = value;
            }
        }

        // 填充桶数组
        int bucketNums = max - min + 1;
        int[] bucketArray = new int[bucketNums];
        for (int value : array) {
            bucketArray[value - min]++;
        }

        // 叠加桶数组
        int count = 0;
        for (int p = 0; p < bucketNums; p++) {
            int temp = bucketArray[p];
            bucketArray[p] += count;
            count += temp;
        }

        // 根据桶数组和原数组，填充新数组
        int[] newArray = new int[array.length];
        for (int value : array) {
            int bucketIndex = value - min;
            int newIndex = --bucketArray[bucketIndex];
            newArray[newIndex] = value;
        }
        return newArray;
    }
}
