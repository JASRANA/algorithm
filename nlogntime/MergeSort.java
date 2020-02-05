package nlogntime;

import java.util.Arrays;

/**
 * 4、归并排序
 *
 * 时间复杂度：
 * 空间复杂度：O(n)
 * 稳定性：稳定
 * 是否基于交换：否
 *
 * 批改：时间复杂度：O(nlogn)  可以把时间拆分成 T(n) = 2 * T(n/2) + n（n为 merge 使用时间），
 *                          累加便可得到T(n) = 2^k * T(n/2^k) + k*n。当 n 为 1 时可得 k = logn，
 *                          带入得到 T(n)=Cn+nlogn，即O(nlogn)。
 *      空间复杂度：O(n)  因为额外开辟的内存空间仅在 merge 中使用，结束后便会释放，因此不累加。
 */
public class MergeSort {
    public int[] mergeSort(int[] array) {
        return diverse(array, 0, array.length-1);
    }

    private int[] diverse(int[] array, int p, int q) {
        if (p >= q) {
            return Arrays.copyOfRange(array, q, q+1);
        }
        int r = (p+q)/2;
        int[] subArray1 = diverse(array, p, r);
        int[] subArray2 = diverse(array, r+1, q);
        return merge(subArray1, subArray2);
    }

    private int[] merge(int[] array1, int[] array2) {
        int[] array = new int[array1.length+array2.length];
        int i = 0, j = 0;
        int t = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                array[t++] = array1[i++];
            }
            else {
                array[t++] = array2[j++];
            }
        }
        while (i < array1.length) {
            array[t++] = array1[i++];
        }
        while (j < array2.length) {
            array[t++] = array2[j++];
        }
        return array;
    }
}
