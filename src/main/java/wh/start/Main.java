package wh.start;

import sort.MergeSortR;
import sort.Qsort3waysR;
import utils.ArrayUtils;

import java.lang.reflect.InvocationTargetException;

public class Main {
    //        Collections.swap();
    //        System.out.println(Lists.newArrayList(1, 2, 3, 4, 5));
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        testInsertionSort();
    }


    static void testInsertionSort() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 三个算法对比， 时间insertSort < mergeSortBU < mergeSort, 需要两百万的数据量
//        Integer[] arr = ArrayUtils.generateNearlyOrderArray(2000000, 100);

        Integer[] arr = ArrayUtils.generateRandomArray(20000, 1, 20000);
        Integer[] arr2 = arr.clone();

        int[] arr3 = ArrayUtils.generateRandomArray2(20000, 1, 20000);

        ArrayUtils.testTime("mergeSort", MergeSortR.class, arr);
        ArrayUtils.testTime("mergeSortBU2", MergeSortR.class, arr2);
//        ArrayUtils.testTime("insertSort", ListSort.class, arr3);
        ArrayUtils.testTime("quickSort", Qsort3waysR.class, arr3);

////        ArrayUtils.printArray(tt);
//        Integer[] arr = ArrayUtils.generateRandomArray(20000, 1, 20000);
//
//        // 1 万个元素中，有200个乱序
//        Integer[] arr2 = ArrayUtils.generateNearlyOrderArray(20000, 100);
//        try {
//            ArrayUtils.testTime("insertSort", ListSort.class, arr);
//
//            // 近乎有序数组 明显更快
//            ArrayUtils.testTime("insertSort", ListSort.class, arr2);
//        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
