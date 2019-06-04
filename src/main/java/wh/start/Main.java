package wh.start;

import com.google.common.collect.Lists;
import sort.ListSort;
import utils.ArrayUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    //        Collections.swap();
    //        System.out.println(Lists.newArrayList(1, 2, 3, 4, 5));
    public static void main(String[] args) {
        testInsertionSort();

    }

    static void testInsertionSort() {
        Integer[] arr = ArrayUtils.generateRandomArray(20000, 1, 20000);

        // 1 万个元素中，有200个乱序
        Integer[] arr2 = ArrayUtils.generateNearlyOrderArray(20000, 100);
        try {
            ArrayUtils.testTime("insertSort", ListSort.class, arr);

            // 近乎有序数组 明显更快
            ArrayUtils.testTime("insertSort", ListSort.class, arr2);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
