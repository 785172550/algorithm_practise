package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ByteDance {

  public static void main(String[] args) {
    ByteDance byteDance = new ByteDance();

    // C(n,k)
    byteDance.combinePath(4, 2, 1, new LinkedList<>());
    // res 表示隔板的位置
    byteDance.res.forEach(System.out::println);
  }

  // 一个m长的线段分成n份，一共多少种分法 -> 思路 类似一个组合问题C(n,k)
  // 用隔板法分成 n份需要 (n-1)个隔板， 一共有(m-1)个空隙

  // 所以是C(m-1,n-1) 例如m=4,n=2 -> C(3,1) = 3 种： 1 3, 2 2, 3 1
  // m=5 n=2 -> C(4,1) = 4 种： 1 4， 2 3， 3 2， 4 1
  // m=5 n=3 -> C(4,2) = 6 种： 1 1 3，1 2 2, 1 3 1，2 1 2，2 2 1，3 1 1
  private int solution(int m, int n) {
    return combine(m - 1, n - 1);
  }

  // C(n,k) = A(n,k) / A(k,k) 这个是只求个数
  private int combine(int n, int k) {
    int nkSum = 1;
    for (int i = n; i >= (n - k + 1); i--) {
      nkSum = n * nkSum;
    }

    int kkSum = 1;
    for (int i = k; i <= k; i++) {
      kkSum = i * kkSum;
    }
    return nkSum / kkSum;
  }

  // 求出C(n,k) 的所有组合 需要用回溯
  // 回溯要想象一个 多叉树上面遍历 收集遍历的Path
  List<List<Integer>> res = new ArrayList<>();

  // index 表示隔板法 板的位置 start from 1
  private void combinePath(int n, int k, int index, LinkedList<Integer> path) {
    if (path.size() == k) {
      res.add(new LinkedList<>(path));
      return;
    }

    for (int i = index; i <= n; i++) {
      path.add(i);
      combinePath(n, k, i + 1, path);
      path.removeLast();
    }
  }

  /*

  回溯算法的框架

  result = []
  def backtrack(path, select):
    if(end condition):
      result.add(path)
      return
    for (s in select):
      做出选择
      backtrack(path, select)
      撤销选择
   */
}
