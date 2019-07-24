package leetcode;

public class SortColor75 {
	public void sortColors(int[] nums) {
        Qsort3ways(nums, 0, nums.length - 1);
    }
    
    private static void Qsort3ways(int[] arr, int left, int right) {

		if (left >= right) {
			return;
		}
		// if (right - left <= 15) {
		// insertSort2(arr, left, right);
		// return;
		// }

		// 随机选取一个 轴点
		// ArrayUtils.swap(arr, left, (int) (Math.random() * (right - left + 1) + left));
		// Integer t = arr[left];
        int t = 1;

		// partitions
		int lt = left -1; // [left ...lt] < t
		int gt = right + 1; // [gt...right] > t
		int eq = left; // [lt + 1...eq) == t
		while (eq < gt) {
			if (arr[eq] < t) {
				lt++;
				// ArrayUtils.swap(arr, lt, eq);
                arr[eq] = arr[lt];
                arr[lt] = 0;
				eq++;
			} else if (arr[eq] > t) {
				gt--;
				// ArrayUtils.swap(arr, gt, eq);
                arr[eq] = arr[gt];
                arr[gt] = 2;
			} else { // (arr[eq] == t)
				eq++;
			}
		}

		// ArrayUtils.swap(arr, lt, left);
		// Qsort(arr, left, lt - 1); // swap(arr, lt, left)
		// Qsort(arr, gt, right);
	}
		
}
