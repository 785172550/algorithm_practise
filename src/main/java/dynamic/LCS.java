package dynamic;

import utils.ArrayUtils;

// 公共最长子序列问题 s1 s2 两个字符串的公共最长子序列。 可以有连续的，也可以不连续
/*
  LCS（最长公共子序列、最长公共子串）

  分别
  连续
  不连续 Leetcode 583

   状态转移方程：
   LCS(m,n) 表示[0...m] [0...n] 两个字符串的 LCS

   if s1[m] == s2[n]: LCS(m,n) = LCS(m-1,n-1) + 1
   if s1[m] != s2[n]: LCS(m,n) = max(LCS(m-1,n), LCS(m,n-1))

   感觉动规都可以转化成一个 二维数组的操作

 */
public class LCS {

  public static void main(String[] args) {
//    "park"
//    "spake"

//    "zoologicoarchaeologist" 22
//    "zoogeologist" 12
    minDistance("zoologicoarchaeologist", "zoogeologist");
  }

  // Leetcode 583
  /*
    Input: "sea", "eat"
    Output: 2
    Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
   */
  public static int minDistance(String word1, String word2) {
    char[] c1 = word1.toCharArray();
    char[] c2 = word2.toCharArray();
    int len1 = c1.length; // 列
    int len2 = c2.length; // 行
    if (len1 == 0)
      return len2;
    if (len2 == 0)
      return len1;

    int grid[][] = new int[len2 + 1][len1 + 1];
    // len1, len2 +1 之后grid 0行，0列都为0， 便于处理 i-1，j-1 的情况
//    int commonSubLen = 0;
//    for (int i = 0; i < len1; i++) { // 第一行 各列
//      if (c1[i] == c2[0]) {
//        Arrays.fill(grid[0][i]);
//        commonSubLen = 1;
//      }
//    }
//
//    for (int i = 1; i < len2; i++) { // 第一列 各行
//      if (c1[0] == c2[i]) {
//        grid[i][0] = 1;
//        commonSubLen = 1;
//      }
//    }

    ArrayUtils.printArray(grid[0]);
    for (int i = 1; i <= len2; i++) {
      for (int j = 1; j <= len1; j++) {
        if (c2[i - 1] == c1[j - 1])  // 对i行 比较 j列的
          grid[i][j] = grid[i - 1][j - 1] + 1;
        else
          grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]); // 不要求连续 子序列
      }
      ArrayUtils.printArray(grid[i]);
    }
//    System.out.println(grid[len2][len1]);
    return len1 + len2 - 2 * grid[len2][len1];
  }
}
