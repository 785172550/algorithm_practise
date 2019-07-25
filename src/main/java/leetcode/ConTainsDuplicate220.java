package leetcode;

import java.util.TreeSet;

public class ConTainsDuplicate220 {

  public static void main(String[] args) {

  }

  // 是否存在 num[i] num[j] 满足 num[i] num[j]的差 < t 且 i j 的差< k
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (k < 1 || t < 0)
      return false;
    TreeSet<Long> recorod = new TreeSet<>();

    for (int i = 0; i < nums.length; i++) {
      long num = nums[i];  // 转为 Long 型，避免了整形溢出情况。
      Long floor = recorod.floor(num + t); // 小于等于
      Long ceil = recorod.ceiling(num - t); // 大于等于
      if ((floor != null && floor >= num) || (ceil != null && ceil <= num))
        return true;

      recorod.add(num);
      if (i >= k)
        recorod.remove((long) (nums[i - k]));
    }
    return false;
  }

}
