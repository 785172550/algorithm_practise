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

    // -----------------------------------------------
    
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
    
    // ------------------------------------------------
    
      /**
   * O(N*logN) recursive 递归 mergeSort
   *
   * @param arr
   */
  public static void mergeSort(Comparable[] arr) {
    if (arr.length <= 1) {
      return;
    }

    mergeSort(arr, 0, arr.length - 1);
  }

  private static void mergeSort(Comparable[] arr, int left, int right) {
    if (left >= right) { // 可以用insertionSort 优化
      return;
    }

    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, mid, left, right);
  }

  // [left...mid]  [mid+1...right] merge 到 arr[left...right]
  private static void merge(Comparable[] arr, int mid, int left, int right) {
    int len = right - left + 1;
    Comparable aux[] = new Comparable[len];
    for (int i = 0; i < len; i++) {
      aux[i] = arr[left + i];
    }
    int j = mid + 1;
    int i = left;

    for (int k = left; k <= right; k++) {
      if (i > mid) {
        arr[k] = aux[j - left];
        j++;
      } else if (j > right) {
        arr[k] = aux[i - left];
        i++;
      } else if (aux[i - left].compareTo(aux[j - left]) < 0) {
        arr[k] = aux[i - left];
        i++;
      } else {
        arr[k] = aux[j - left];
        j++;
      }
    }
  }
    
    


}
