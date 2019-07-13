package graph.Prim;

import graph.Edge;
import graph.WeightedGraph;
import tree.heap.IndexMinHeapTemplate;

import java.util.List;

public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph G;
    private IndexMinHeapTemplate<Edge<Weight>> heap; // 最小堆， 辅助储存临界边中的最小值
    private boolean marked[]; // 标记数组， 记录节点是否被访问
    private List<Edge<Weight>> mst; //最小生成树的所有边
    private Number mstWeigth; // 最小生成树的权值
}
