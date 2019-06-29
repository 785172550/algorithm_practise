package sort;

public class MergeSortR {

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
//            insertSort2(arr, i, Math.min(i + 15, len - 1));
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
}
