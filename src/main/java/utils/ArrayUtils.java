package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayUtils {


  public static <T> void swap(T[] arr, int first, int second) {
    if (first > arr.length || second > arr.length) {
      throw new IllegalArgumentException("out of bounds");
    }
    T temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }

  public static void swap(int[] arr, int first, int second) {
    if (first > arr.length || second > arr.length) {
      throw new IllegalArgumentException("out of bounds");
    }
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }

  /**
   * @return 是否从小到大排序好，大小根据Comparator定义
   */
  public static <T> boolean isSorted(T[] arr, Comparator<? super T> c) {
    for (int i = 1; i < arr.length; i++) {
      if (c.compare(arr[i - 1], arr[i]) > 0) {
        return false;
      }
    }
    return true;
  }

  public static void printArray(Integer[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i != arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void printArray(int[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i]);
      if (i != arr.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  // arrays.aslist() 生成的list无法添加修改
  public static <T> List<T> arrToList(T[] arr) {
    List<T> list = new ArrayList<>();
    Collections.addAll(list, arr);
    return list;
  }

  // 随机生成数组，元素范围[min,max]
  public static Integer[] generateRandomArray(int n, int min, int max) {
    Integer[] arr = new Integer[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (int) (Math.random() * (max - min + 1) + min);
    }
    return arr;
  }

  public static int[] generateRandomArray2(int n, int min, int max) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (int) (Math.random() * (max - min + 1) + min);
    }
    return arr;
  }

  // 近乎有序的数组
  public static int[] generateNearlyOrderArray2(int n, int swapTimes) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i;
    }

    // random swap
    for (int i = 0; i < swapTimes; i++) {
      int rand1 = (int) (Math.random() * n);
      int rand2 = (int) (Math.random() * n);
      swap(arr, rand1, rand2);
    }

    return arr;
  }

  public static Integer[] generateNearlyOrderArray(int n, int swapTimes) {
    Integer[] arr = new Integer[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i;
    }

    // random swap
    for (int i = 0; i < swapTimes; i++) {
      int rand1 = (int) (Math.random() * n);
      int rand2 = (int) (Math.random() * n);
      swap(arr, rand1, rand2);
    }

    return arr;
  }

  public static void testTime(String methodName, Class<?> clz, Comparable[] arr)
          throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method sortMethod = clz.getMethod(methodName, new Class[]{Comparable[].class});
    Object[] params = new Object[]{arr};

    long start = System.nanoTime();
    sortMethod.invoke(null, params);
    long end = System.nanoTime();
    long duration = end - start;
    long duration2 = end / (1000 * 1000) - start / (1000 * 1000);

    System.out.println("method name: " + methodName + " time: " + duration / 1000 + " microsecond 微秒, " + duration2 + " millsecond 毫秒");
  }

  public static void testTime(String methodName, Class<?> clz, int[] arr)
          throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Method sortMethod = clz.getMethod(methodName, new Class[]{int[].class});
    Object[] params = new Object[]{arr};

    long start = System.nanoTime();
    sortMethod.invoke(null, params);
    long end = System.nanoTime();
    long duration = end - start;
    long duration2 = end / (1000 * 1000) - start / (1000 * 1000);

    System.out.println("method name: " + methodName + " time: " + duration / 1000 + " microsecond 微秒, " + duration2 + " millsecond 毫秒");
  }

}
