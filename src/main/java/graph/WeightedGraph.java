package graph;

import java.util.List;

public interface WeightedGraph<Weight extends Number & Comparable> {
    int V();
    int E();
    void addEdge(Edge<Weight> e);
    boolean hasEdge(int v, int w);
    void show();
    List<Edge<Weight>> adj(int v);
}
