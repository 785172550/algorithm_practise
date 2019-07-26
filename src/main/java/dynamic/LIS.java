package dynamic;

import java.util.Arrays;


/*
  LIS（最长递增子序列）、

  分别
  连续 Leetcode 674
  不连续 Leetcode 300

 */
public class LIS {

  public static void main(String[] args) {
    int[] t = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(lengthOfLIS(t));
    System.out.println(lenOfLIS2(t));
  }

  // O(n2),
  public static int lengthOfLIS(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, 1);

    for (int i = 1; i < nums.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (nums[j] < nums[i]) {
          memo[i] = Math.max(memo[j] + 1, memo[i]); // nums[0 ... i-1]与num[i]比较，如果num[i]大， memo[i]至少为meno[j]+1
        }
      }
    }
    Arrays.sort(memo); // 找出memo 最大值

    return memo[nums.length - 1];
  }

  // nums = [3,5,6,2,4,5,7]
  /*
   memo
   0-3
   1-5
   2-6
   0-2
   1-4
   0-5
   */
  //  LIS 问题可以有O(nlogn)的 解法
  public static int lenOfLIS2(int[] nums) {
    int[] memo = new int[nums.length]; // tail of
    int max_len = 0;

    for (int i = 0; i < nums.length; i++) {
      int k = 0;
      int j = max_len; //二分查找的两个指针
      while (k < j) {
        int m = (k + j) / 2; // 中间值
        if (memo[m] < nums[i])  // 当查询的值比 nums[i]小时，改变首指针
          k = m + 1;
        else
          j = m;
      }
      memo[k] = nums[i];
      max_len = Math.max(k + 1, max_len);
    }
    return max_len;
  }
}
