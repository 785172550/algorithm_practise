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

    // [left...right]
    public static void insertSort2(Comparable[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
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

        if (arr[mid].compareTo(arr[mid + 1]) > 0) // **在近乎有序的数组里很重要 to avoid array already order
            merge(arr, mid, left, right); // sort array from less to large
    }

    // [left...mid]  [mid+1...right] merge 到 arr[left...right]
    private static void merge(Comparable[] arr, int mid, int left, int right) {
        int len = right - left + 1;
        Comparable aux[] = new Comparable[len];
//        for (int i = 0; i < len; i++) {
//            aux[i] = arr[left + i];
//        }
        System.arraycopy(arr, left, aux, 0, len);

        int j = mid + 1;
        int i = left;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = aux[j - left];
                j++;
            } else if (j > right) {
                arr[k] = aux[i - left];
                i++;
            } else if (aux[i - left].compareTo(aux[j - left]) <= 0) {
                arr[k] = aux[i - left];
                i++;
            } else {
                arr[k] = aux[j - left];
                j++;
            }
        }
    }

    // merge sort buttom up 自底向下
    public static void mergeSortBU(Comparable[] arr) {
        int len = arr.length;

        for (int step = 1; step < len; step += step) { // each merge step length
            for (int i = 0; i + step < len; i += 2 * step)
                // 对arr[i...i+step-1] arr[i+step...i+2*step-1] merge
                // i+step 和 i+2*step-1 都要<n, 防止越界
                if (arr[i + step - 1].compareTo(arr[i + step]) > 0)
                    merge(arr, i + step - 1, i, Math.min(i + 2 * step - 1, len - 1));
        }
    }

    public static void mergeSortBU2(Comparable[] arr) {
        int len = arr.length;

        // 近乎有序
        for (int i = 0; i < len; i += 16) {
            insertSort2(arr, i, Math.min(i + 15, len - 1));
        }

        for (int step = 16; step < len; step += step) { // each merge step length
            for (int i = 0; i + step < len; i += 2 * step) {
                // 对arr[i...i+step-1] arr[i+step...i+2*step-1] merge
                // i+step 和 i+2*step-1 都要<n, 防止越界
                if (arr[i + step - 1].compareTo(arr[i + step]) > 0)
                    merge(arr, i + step - 1, i, Math.min(i + 2 * step - 1, len - 1));
            }

        }
    }


    // ----------------------------------------------------------

    // O(N*logN) recursive 3 ways
    public static void quickSort(Comparable[] arr) {
        int len = arr.length;
        Qsort(arr, 0, len - 1);
    }

    private static void Qsort(Comparable[] arr, int left, int right) {

//        if (left >= right) {
//            return;
//        }
        if (right - left <= 15) {
            insertSort2(arr, left, right);
            return;
        }

        // 随机选取一个 轴点
        ArrayUtils.swap(arr, left, (int) (Math.random() * (right - left + 1) + left));
        Comparable t = arr[left];

        // partitions
        int lt = left; // [left...lt] < t
        int gt = right; // [gt...right] > t
        int eq = left; // [lt...eq) == t
        while (eq < gt) {
            if (arr[eq].compareTo(t) < 0) {
                ArrayUtils.swap(arr, lt, eq);
                lt++;
                eq++;
            }
            if (arr[eq].compareTo(t) > 0) {
                ArrayUtils.swap(arr, gt, eq);
                gt--;
            }
            if (arr[eq].compareTo(t) == 0) {
                eq++;
            }
        }

        ArrayUtils.swap(arr, lt, left);
        Qsort(arr, left, lt - 1); // swap(arr, lt, left)
        Qsort(arr, gt, right);

    }
}
