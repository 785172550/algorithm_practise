package graph;


public interface Graph {
  public int V();

  public int E();

  public void addEdge(int v, int w);

  boolean hasEdge(int v, int w);

  void show();

  public Iterable<Integer> adj(int v); // 节点V的所有相邻点
}
