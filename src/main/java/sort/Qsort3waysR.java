package sort;

import utils.ArrayUtils;

public class Qsort3waysR {
    // ----------------------------------------------------------

    // O(N*logN) recursive 3 ways
    public static void quickSort(int[] arr) {
        int len = arr.length;
        Qsort(arr, 0, len - 1);
    }

    private static void Qsort(int[] arr, int left, int right) {
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

        // partitions
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
        Qsort(arr, left, lt - 1); // lt-1 casue that: swap(arr, lt, left)
        Qsort(arr, gt, right);
    }
}
