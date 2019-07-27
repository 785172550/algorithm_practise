package backtracking;

import java.util.Stack;

// 典型的 DFS 算法问题
public class FloodFill {

  public static void main(String[] args) {
  }

  private int[][] near = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  private int[][] grid1;
  private char[][] grid2;

  //  Max area of island leetcode 695
  public int maxAreaOfIsland(int[][] grid) {
    this.grid1 = grid;
    int rows = grid1.length;
    if (rows == 0)
      return 0;

    int cols = grid1[0].length;
    int max_area = 0;
    for (int i = 0; i < rows; i++) {  // 每一行
      for (int j = 0; j < cols; j++) { // 每一列
        if (grid1[i][j] == 1) {
          grid1[i][j] = 0; // visited
          max_area = Math.max(1 + dfs_area(i, j), max_area);
//          max_area = Math.max(1 + dfs_areaNR(i, j), max_area);
        }
      }
    }
    return max_area;
  }

  private int dfs_area(int i, int j) {
    int area = 0;
    for (int k = 0; k < 4; k++) {
      int newI = i + near[k][0];
      int newJ = j + near[k][1];
      if (newI < 0 || newJ < 0 || newI >= grid1.length || newJ >= grid1[0].length)
        continue;
      if (grid1[newI][newJ] == 1) {
        grid1[newI][newJ] = 0; // visited
        area = 1 + dfs_area(newI, newJ); // 1 代表将 newI newJ 加入面积
      }
    }
    return area;
  }

  private int dfs_areaNR(int i, int j) {
    Stack<Pair> stack = new Stack<>();
    stack.push(new Pair(i, j)); // 压入第一个
    int area = 0;

    while (!stack.isEmpty()) {
      Pair pair = stack.pop();

      for (int k = 0; k < 4; k++) {
        int newI = pair.i + near[k][0];
        int newJ = pair.j + near[k][1];
        if (newI < 0 || newJ < 0 || newI >= grid1.length || newJ >= grid1[0].length)
          continue;
        if (grid1[newI][newJ] == 1) {
          grid1[newI][newJ] = 0; // visited
          stack.push(new Pair(newI, newJ));
          area += 1;
        }
      }
    }
    return area;
  }

  // -------------------------
  // number of island leetcode 200: 1是岛屿，0是海
  // 思路， dfs深度优先遍历 上下左右是否 ==1

  public int numIslands(char[][] grid) {
    this.grid2 = grid;
    int rows = grid2.length;
    if (rows == 0)
      return 0;

    int cols = grid2[0].length;
    int count = 0;
    for (int i = 0; i < rows; i++) {  // 每一行
      for (int j = 0; j < cols; j++) { // 每一列
        if (grid2[i][j] == '1') {
          count += 1;       // 有一个独立的岛屿
          grid2[i][j] = '0'; // visited
          dfs(i, j);
        }
      }
    }
    return count;
  }

  private void dfs(int i, int j) {
    for (int k = 0; k < 4; k++) {
      int newI = i + near[k][0];
      int newJ = j + near[k][1];
      if (newI < 0 || newJ < 0 || newI >= grid2.length || newJ >= grid2[0].length)
        continue;
      if (grid2[newI][newJ] == '1') {
        grid2[newI][newJ] = '0'; // visited
        dfs(newI, newJ);
//        dfsNR(newI,newJ);
      }
    }
  }

  // dfs 递归改迭代
  private void dfsNR(int i, int j) {
    Stack<Pair> stack = new Stack<>();
    stack.push(new Pair(i, j)); // 压入第一个

    while (!stack.isEmpty()) { //
      Pair pair = stack.pop();
      for (int k = 0; k < 4; k++) {
        int newI = pair.i + near[k][0];
        int newJ = pair.j + near[k][1];
        if (newI < 0 || newJ < 0 || newI >= grid2.length || newJ >= grid2[0].length)
          continue;
        if (grid2[newI][newJ] == '1') {
          grid2[newI][newJ] = '0'; // visited
          stack.push(new Pair(newI, newJ)); // 遍历到符合的就 压栈 到时候在弹出遍历周边
        }
      }
    }
  }

  class Pair {

    int i;
    int j;

    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
}
