package com.wh.test.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Triangle120 {

  public int minimumTotal(List<List<Integer>> triangle) {

    int n = triangle.size();
    int sum = backTracking(triangle, n);
    return sum;
  }

  public int backTracking(List<List<Integer>> triangle, int level) {

    if (level == 0) {
      return 0;
    }
    if (level == 1) {
      return triangle.get(level - 1).get(0);
    }
    //
    int levelSize = triangle.size();
    // int[] val = new int[triangle.get(levelSize - 1).size()];

    for (int i = 1; i < levelSize; i++) {
      int cols = triangle.get(i).size();

      for (int j = 0; j < cols; j++) {
        if (j == 0) {
          int res = triangle.get(i).get(0) + triangle.get(i - 1).get(0);
          triangle.get(i).set(0, res);
        } else if (j == cols - 1) {
          int res = triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1);
          triangle.get(i).set(j, res);
        } else {
          int res =
              triangle.get(i).get(j)
                  + Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j));
          triangle.get(i).set(j, res);
        }
      }
    }

    List<Integer> val = triangle.get(levelSize - 1);
    Collections.sort(val);
    return val.get(0);
  }

  public int minimumTotal2(List<List<Integer>> triangle) {
    int m = triangle.size();
    int n = triangle.get(m - 1).size();
    int[][] memo = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        memo[i][j] = -1;
      }
    }
    return dfs(triangle, 0, 0, memo);
  }

  private int dfs(List<List<Integer>> triangle, int i, int j, int[][] memo) {
    if (i == triangle.size()) {
      return 0;
    }
    if (memo[i][j] != -1) {
      return memo[i][j];
    }
    int L = dfs(triangle, i + 1, j, memo);
    int R = dfs(triangle, i + 1, j + 1, memo);
    memo[i][j] = triangle.get(i).get(j) + Math.min(L, R);
    return memo[i][j];
  }

  public static void main(String[] args) {
    Triangle120 t = new Triangle120();
    List<List<Integer>> triangle = new ArrayList<>();
    triangle.add(Arrays.asList(2));
    triangle.add(Arrays.asList(2, 3));
    triangle.add(Arrays.asList(6, 5, 7));
    triangle.add(Arrays.asList(4, 3, 8, 3));
    t.minimumTotal2(triangle);
  }
}
