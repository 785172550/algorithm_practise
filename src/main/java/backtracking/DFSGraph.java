package backtracking;

// dfs 判断 有向图是否有环 ， 邻接矩阵: O(v^2) 邻接表O(v+e)
public class DFSGraph {

  public static void main(String[] args) {

    int[][] graph = new int[][]{};
  }

  int[][] graph;
  boolean[] visited; // len = v
  boolean isCycle = false;

  boolean isCycle(int[][] graph) {
    int row = graph.length;
//    int col = graph[0].length;

    for (int i = 0; i < row; i++) {
      if (!visited[i])
        dfs(0);
    }
    return isCycle;

  }

  private void dfs(int j) {
    visited[j] = true;
    int[] adj = getAdj(j); // 获得所有临边
    for (int col = 0; col < adj.length; col++) {
      if (j == col) // 跳过自己
        continue;
      if (adj[col] == 1) { // 临边存在

        if (visited[col]) {
          isCycle = true;
          return;
        } else {
          dfs(adj[col]);
        }

      }
    }

  }

  private int[] getAdj(int v) {
    return graph[v];
  }
}
