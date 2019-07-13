package graph.Prim;

import graph.Edge;
import graph.WeightedGraph;
import java.util.List;
import tree.heap.IndexMinHeapTemplate;

public class PrimMST<Weight extends Number & Comparable> {

  private WeightedGraph G;
  private IndexMinHeapTemplate<Edge<Weight>> iHeap; // 最小堆， 辅助储存临界边中的最小值
  private boolean marked[]; // 标记数组， 记录节点是否被访问
  private List<Edge<Weight>> mst; //最小生成树的所有边
  private Number mstWeigth; // 最小生成树的权值

  private Edge<Weight>[] edgeTo; // 访问节点所对应的边

  public PrimMST(WeightedGraph graph) {
    G = graph;
    assert G.E() > 0;
    init();
  }

  private void init() {
    iHeap = new IndexMinHeapTemplate<>(G.V());
    marked = new boolean[G.V()];
    edgeTo = new Edge[G.V()];

  }

}
