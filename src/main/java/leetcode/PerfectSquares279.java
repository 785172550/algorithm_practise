package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PerfectSquares279 {
  // Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4,
  // 9, 16, ...) which sum to n.
  // 这个问题 用贪心算法 是不成立的
  // 建立 一个有向无权 无环图 DAG 找最短路径 bfs
  public static void main(String[] args) {
    System.out.println(numSquares(7168));
  }

  // 性能问题 n = 7168
  public static int numSquares(int n) {
    Queue<Pair<Integer, Integer>> q = new LinkedList<>();
    q.offer(new Pair<Integer, Integer>(n, 0)); // 对于n 这个数字 0步就可以到达

    while (!q.isEmpty()) {
      int num = q.peek().key;
      int step = q.peek().val;
      q.poll();

      if (num == 0) return step;
      for (int i = 1; num >= i * i; i++) {
        q.offer(new Pair<Integer, Integer>(num - i * i, step + 1));
      }
    }
    return 0;
  }

  // 记录已经访问过的节点， 没访问过的节点才取入队， 此时的step>=之前入队的时候
  public static int numSquares2(int n) {
    Queue<Pair<Integer, Integer>> q = new LinkedList<>();
    q.offer(new Pair<Integer, Integer>(n, 0)); // 对于n 这个数字 0步就可以到达

    boolean[] visited = new boolean[n];

    while (!q.isEmpty()) {
      int num = q.peek().key;
      int step = q.peek().val;
      q.poll();

      for (int i = 1; num >= i * i; i++) {
        int newNum = num - i * i;
        if (newNum < 0) break;
        else if (newNum == 0) return step + 1; // 直接返回， 不用入队

        if (!visited[newNum]) {
          q.offer(new Pair<Integer, Integer>(newNum, step + 1));
          visited[newNum] = true;
        }
      }
    }
    return 0;
  }
}

class Pair<K, V> {
  K key;
  V val;

  public Pair(K key, V val) {
    this.key = key;
    this.val = val;
  }
}
