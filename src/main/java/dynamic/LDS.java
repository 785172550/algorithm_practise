package dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LDS 最长不含重复字符子字串
 * <p>
 * if set.notContain(n) : LDS(n) = LDS(n-1) + 1 else: LDS(n) = 1 ; set.clear()
 * <p>
 * 华为
 */
public class LDS {
  public static void main(String[] args) {

    LDS("zoogeologist");
    System.out.println(LDS_window("zoogeoqlogist"));
  }

  // 错误的算法
  public static void LDS(String str) {
    int[] meno = new int[str.length()];
    Arrays.fill(meno, 1);
    Set<Character> set = new HashSet<>();
    set.add(str.charAt(0));

    for (int i = 1; i < str.length(); i++) {
      //str.charAt(i) == str.charAt(i-1) 作为优化提前结束判断
      if (str.charAt(i) == str.charAt(i - 1)) {
        continue;
      }

      if (!set.contains(str.charAt(i))) {
        meno[i] = meno[i - 1] + 1;
        set.add(str.charAt(i));
      } else {
        set.clear();
        set.add(str.charAt(i));
      }
    }

    Arrays.sort(meno);
    System.out.println(meno[meno.length - 1]);
  }

  // 滑动窗口 解法
  private static int LDS_window(String str) {
    int left = 0;
    int right = 1; // 双指针

    int[] dict = new int[256]; // 字典 判重
    char[] chars = str.toCharArray();

    dict[chars[left]] = 1;
    int maxLen = 1;

    // 滑动窗口 [left .. right] 之中没有重复字符
    for (int i = 1; i < chars.length; i++) {
      right = i; //right 右滑
      dict[chars[i]] = dict[chars[i]] + 1;

      while (dict[chars[i]] > 1) { // 如果重复 left右滑
        dict[chars[left]] = dict[chars[left]] - 1;
        left = left + 1;
      }

//      System.out.println(left + ".." + right);
      maxLen = Math.max(maxLen, right - left + 1);
    }


    return maxLen;
  }
}
