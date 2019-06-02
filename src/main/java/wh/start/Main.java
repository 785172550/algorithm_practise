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

        Integer[] arr = ArrayUtils.generateRandomArray(100000, 1, 1000);
        // 近乎有序数组 明显更快
        Integer[] arr2 = ArrayUtils.generateNearlyOrderArray(100000, 200);
        try {
            ArrayUtils.testTime("insertSort", ListSort.class, arr);
            ArrayUtils.testTime("insertSort", ListSort.class, arr2);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
