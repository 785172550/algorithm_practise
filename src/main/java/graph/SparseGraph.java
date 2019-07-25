package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class SparseGraph implements Graph {

  private int n; // 节点数
  private int m; // 边数
  private boolean directed; // 是否为有向图
  private LinkedList<Integer>[] g; // 图的具体数据

  // 构造函数
  @SuppressWarnings("unchecked")
  public SparseGraph(int n, boolean directed) {
    assert n >= 0;
    this.n = n;
    this.m = 0; // 初始化没有任何边
    this.directed = directed;
    // g初始化为n个空的vector, 表示每一个g[i]都为空, 即没有任和边
    g = (LinkedList<Integer>[]) new LinkedList[n];
    for (int i = 0; i < n; i++) g[i] = new LinkedList<Integer>();
  }

  @Override
  public int V() {
    return n;
  }

  @Override
  public int E() {
    return m;
  }

  @Override
  public void addEdge(int v, int w) {
    if (directed) g[v].add(w);
    else if (v != w) g[w].add(v); // 无向图
    m++;
  }

  @Override
  public boolean hasEdge(int v, int w) {
    Iterator<Integer> edges = adj(v).iterator();
    while (edges.hasNext()) {
      if (edges.next() == w) return true;
    }
    return false;
  }

  @Override
  public void show() {
    for (int i = 0; i < n; i++) {
      System.out.print("vertex " + i + ":\t");
      for (int j = 0; j < g[i].size(); j++) System.out.print(g[i].get(j) + "\t");
      System.out.println();
    }
  }

  @Override
  public Iterable<Integer> adj(int v) {
    assert v >= 0 && v < n;
    return g[v];
  }
}
