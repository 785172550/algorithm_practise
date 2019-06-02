package sort;

import utils.ArrayUtils;

import java.util.Comparator;


public class ListSort {

    /**
     * selection sort 选择排序法O(n^2)
     *
     * @param <T> template
     */
    public static <T> void selectionSort(T[] arr, Comparator<? super T> c) {
        for (int i = 0; i < arr.length; i++) {

            // search min in [i,n)
            int minIndex = i;
            for (int j = minIndex + 1; j < arr.length; j++)
                if (c.compare(arr[j], arr[minIndex]) < 0)
                    minIndex = j;

            // swap
            ArrayUtils.swap(arr, i, minIndex);
        }
    }

    // 通常是O(n^2)， 稳定的排序，特别适用于近乎有序的数组
    public static void insertSort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Comparable temp = arr[i];
            int insertIndex;
            // c.compare(arr[insertIndex - 1], temp) > 0  比较如果 arr[insertIndex - 1] > temp
            for (insertIndex = i; insertIndex > 0 && arr[insertIndex - 1].compareTo(temp) > 0; insertIndex--) {
                arr[insertIndex] = arr[insertIndex - 1]; // 一个一个向后移动
            }
            arr[insertIndex] = temp;
        }
        // 还有一种 批量向后挪动的实现方法
    }


}
