package dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * LDS 最长不含重复字符子字串
 *
 */
public class LDS {

  public static void LDS(String str) {
    int[] meno = new int[str.length()];
    Arrays.fill(meno, 1);
    Set<Character> set = new HashSet<>();
    set.add(str.charAt(0));
    for (int i = 1; i < str.length(); i++) {
      if (!set.contains(str.charAt(i)) && str.charAt(i) != str.charAt(i - 1)) {
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
}
