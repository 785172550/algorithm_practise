package sort;

import utils.ArrayUtils;

public class Qsort3waysR {
  // ----------------------------------------------------------
  public static void main(String[] args) {
    Integer[] arr = ArrayUtils.generateRandomArray(200, 1, 200);
    qsort(arr, 0, arr.length - 1);
    ArrayUtils.printArray(arr);
//    qsort3way(arr, 0, arr.length - 1);
  }

  // O(N*logN) recursive 3 ways
  public static void quickSort(Integer[] arr) {
    int len = arr.length;
//    qsort3way(arr, 0, len - 1);
    qsort(arr, 0, len - 1);
  }

  private static void qsort3way(Integer[] arr, int left, int right) {
    // if (right - left <= 15) {
    // insertSort2(arr, left, right);
    // return;
    // }

    if (left >= right) {
      return;
    }

    // 随机选取一个 轴点 random a pivot
    ArrayUtils.swap(arr, left, (int) (Math.random() * (right - left + 1) + left));
    Integer t = arr[left];

    // partition
    int lt = left; // [left+1 ...lt] < t
    int gt = right + 1; // [gt...right] > t
    int eq = left + 1; // [lt + 1...eq) == t
    while (eq < gt) {
      if (arr[eq] < t) {
        lt++;
        ArrayUtils.swap(arr, lt, eq);
        eq++;
      } else if (arr[eq] > t) {
        gt--;
        ArrayUtils.swap(arr, gt, eq);
      } else { // (arr[eq] == t)
        eq++;
      }
    }

    ArrayUtils.swap(arr, lt, left);
    qsort3way(arr, left, lt - 1); // lt-1 casue that: swap(arr, lt, left)
    qsort3way(arr, gt, right);
  }

  // 经典快排
  private static void qsort(Integer[] arr, int left, int right) {
    if (left >= right) {
      return;
    }

    // 随机选取一个 轴点 random a pivot
    ArrayUtils.swap(arr, left, (int) (Math.random() * (right - left + 1) + left));
    Integer t = arr[left];

    // partition
    int j = left;
    int index = left + 1;
    while (index <= right) {
      if (arr[index] < t) {
        ArrayUtils.swap(arr, j + 1, index);
        j++;
      }
      index++;
    }
    ArrayUtils.swap(arr, j, left);
    qsort(arr, left, j - 1);
    qsort(arr, j + 1, right);

  }
}
