package wh.start;

import com.google.common.collect.Lists;
import sort.ListSort;
import utils.ArrayUtils;
import utils.TypeUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Integer[] arr3 = arr.clone();

        ArrayUtils.testTime("mergeSort", ListSort.class, arr);
        ArrayUtils.testTime("mergeSortBU2", ListSort.class, arr2);
//        ArrayUtils.testTime("insertSort", ListSort.class, arr3);
        ArrayUtils.testTime("quickSort", ListSort.class, arr3);

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
