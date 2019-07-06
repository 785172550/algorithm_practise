package com.wh.test.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS300 {

  public static void main(String[] args) {
    // int[] t = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
    // lengthOfLIS(t);
    Integer[] ar1 = new Integer[] {2, 3};
    Integer[] ar2 = new Integer[] {1, 4};
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    list.add(new ArrayList<>(Arrays.asList(ar1)));
    list.add(new ArrayList<>(Arrays.asList(ar2)));
    getAllSolution(list, 4);
  }

    // O(n2), LIS 问题可以有O(nlogn)的解法，但不是动态规划
  public static int lengthOfLIS(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, 1);
    // memo[0] = 1;

    for (int i = 1; i < nums.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (nums[j] < nums[i]) {
          memo[i] = Math.max(memo[j] + 1, memo[i]); // [0 ... i-1] 与 num[i] 比较， 如果num[i]大， memo[i]至少为meno[j]+1
          continue;
        }
      }
    }
    Arrays.sort(memo);

    return memo[nums.length - 1];
  }

  public static List<ArrayList<ArrayList<Integer>>> getAllSolution(
      ArrayList<ArrayList<Integer>> list, int n) {
    List<ArrayList<ArrayList<Integer>>> res = new ArrayList<ArrayList<ArrayList<Integer>>>();

    int[] begins = new int[list.size()];
    begins[0] = 0;
    for (int i = 1; i < begins.length; i++) {
      begins[i] = list.get(i).size() + begins[i - 1];
    }

    for (int i = 1; i < n; i++) {
      res.add(insertCar(begins, i, new ArrayList<>()));
    }
    return res;
  }

  public static ArrayList<ArrayList<Integer>> insertCar(
      int[] begins, int index, List<ArrayList<Integer>> list) {

    for (int i = 0; i < begins.length; i++) {
      if (i == begins[i] && i != 0) {
        list.get(i - 1).add(Integer.MAX_VALUE);
        break;
      } else if (index > begins[i]) {
        list.get(i).add(index - begins[i], 9999);
        break;
      }
    }
    return new ArrayList<>(list);
  }
}
