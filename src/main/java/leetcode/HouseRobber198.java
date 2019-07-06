package com.wh.test.leetcode;

import java.util.Arrays;

public class HouseRobber198 {

  // 考虑抢劫 nums[index ... length -1] 范围的房子
  public int tryRob(int[] nums, int index, int[] memo) {

    if (index >= nums.length) return 0;

    if (memo[index] != -1) {
      return memo[index]; // memo[index] 是考虑抢劫 [index ... n] 的最大收益
    }

    int res = 0;
    for (int i = index; i < nums.length; i++) {
      res =
          Math.max(
              res,
              (nums[i] + tryRob(nums, i + 2, memo))); // compare res with nums[i] + tryRob(nums, i +
      // 2, memo)
    }
    memo[index] = res;
    return res;
  }

  public int rob(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, -1);
    return tryRob(nums, 0, memo);
  }

  // 考虑抢劫 nums[0 ... index] 范围的房子
  public int tryRob2(int[] nums, int index, int[] memo) {

    if (index < 0) return 0;
    if (index < 2) return Math.max(nums[0], nums[index]);
    memo[0] = nums[0];
    memo[1] = Math.max(nums[0], nums[1]);

    // 我自己的思路是：这里我觉得只需要一个循环，我觉得只需要让 memo[i]
    // 在nums[i] + memo[i - 2]和memo[i - 1] 取max，我这样提交leetcode也是对的
    for (int i = 2; i < nums.length; i++) {
      memo[i] = Math.max(nums[i] + memo[i - 2], memo[i - 1]);
    }
    // 我自己理解不了 两层循环的思路:
    // memo[i] = Math.max(memo[i], memo[j], j+2 <n ? memo[j +2]:0);
    // memo[i] 在赋值之前不都是-1 吗， max里的 memo[i]不是永远取不到吗
    return memo[index];
  }

  public int rob2(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, -1);
    return tryRob(nums, nums.length - 1, memo);
  }
}
