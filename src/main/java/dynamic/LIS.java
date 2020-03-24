package dynamic;

import java.util.Arrays;


/*
  LIS（最长递增子序列）、

  分别
  连续 Leetcode 674
  不连续 Leetcode 300

   LIS(n) 表示n长度的字符串的最长递增子序列

   LIS(n) = max(1, 1+ LIS(j))
   j 取值范围 num[n] > num[j]的所有情况

 */
public class LIS {

  public static void main(String[] args) {
    int[] t = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(lengthOfLIS(t));
    System.out.println(lengthOfLISR(t));
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

  // 记忆化搜索
  public static int lengthOfLISR(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, -1);

    helper(nums, memo, nums.length - 1);

    Arrays.sort(memo); // 找出memo 最大值
    return memo[nums.length - 1];
  }

  private static int helper(int[] num, int[] meno, int index) {
    if (index == 0) return 1;
    if (meno[index] != -1) return meno[index];

    int res = 1;
    for (int i = 1; i < index; i++) {
      if (num[index] > num[i]) {
        res = Math.max(res, helper(num, meno, i) + 1);
      }
    }

    meno[index] = res;
    return res;
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
  //  LIS 问题可以有O(nlogn)的 解法： 网上搜 poker牌分堆法
  // https://github.com/labuladong/fucking-algorithm
  public static int lenOfLIS2(int[] nums) {
    int[] memo = new int[nums.length]; // tail of
    int max_len = 0; // 牌堆数

    for (int i = 0; i < nums.length; i++) {
      int left = 0;
      int right = max_len; //二分查找的两个指针 logN
      while (left < right) {
        int m = (left + right) / 2;
        if (memo[m] < nums[i])  // 当查询的值比 nums[i]小时，改变首指针
          left = m + 1;
        else
          right = m;
      }

      memo[left] = nums[i];

      max_len = Math.max(left + 1, max_len);
    }
    return max_len;
  }
}
