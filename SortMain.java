import n2time.BubbleSort;
import n2time.InsertSort;
import n2time.SelectSort;
import nlogntime.MergeSort;
import nlogntime.QuickSort;
import ntime.BucketSort;
import ntime.CountingSort;

import java.util.Random;

public class SortMain {

    private static final int LENGTH = 100000;

    public static void main(String[] args) {

        int[] array = new int[LENGTH];
        Random r = new Random(1);
        for (int i = 0; i < LENGTH; i++) {
            array[i] = r.nextInt(LENGTH*100);
        }

        long startBubble = System.nanoTime();
        int[] bubble = new BubbleSort().bubbleSort(array.clone());
        long endBubble = System.nanoTime();

        long startInsertBad = System.nanoTime();
        int[] insertBad = new InsertSort().insertSortBad(array.clone());
        long endInsertBad = System.nanoTime();

        long startInsert = System.nanoTime();
        int[] insert = new InsertSort().insertSort(array.clone());
        long endInsert = System.nanoTime();

        long startSelect = System.nanoTime();
        int[] select = new SelectSort().selectSort(array.clone());
        long endSelect = System.nanoTime();

        long startMerge = System.nanoTime();
        int[] merge = new MergeSort().mergeSort(array.clone());
        long endMerge = System.nanoTime();

        long startQuick = System.nanoTime();
        int[] quick = new QuickSort().quickSort(array.clone());
        long endQuick = System.nanoTime();

        long startBucket = System.nanoTime();
        int[] bucket = new BucketSort().bucketSort(array.clone(), 100);
        long endBucket = System.nanoTime();

        long startCounting = System.nanoTime();
        int[] counting = new CountingSort().countingSort(array.clone());
        long endCounting = System.nanoTime();

        // 冒泡排序与插入排序都是O(n²)，为什么慢这么多？
        // ————原因：插入排序每次只有一个数据赋值操作；而冒泡排序需要交换元素值，因此有三个数据赋值操作。
        printTime("bubble", startBubble, endBubble);
        printTime("insert", startInsert, endInsert);
        printTime("select", startSelect, endSelect);
        printTime("merge", startMerge, endMerge);
        printTime("quick", startQuick, endQuick);
        printTime("bucket", startBucket, endBucket);
        printTime("counting", startCounting, endCounting);

        // clone 的有趣现象
        // 对原数组修改会影响clone的数组
        // 输出：3，2
        //
        // 原因：clone 是浅拷贝
//        int[][] ints = new int[3][3];
//        int[][] clone = ints.clone();
//        ints[0][0] = 3;
//        System.out.println(clone[0][0]);
//        ints[0][0] = 2;
//        System.out.println(clone[0][0]);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private static void printTime(String name, long start, long end) {

        System.out.println(name + " sort time: " + (end - start) + " ns");
    }
}
