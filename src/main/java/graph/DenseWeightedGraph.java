package graph;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph<Weight> {

    private int n;
    private int m;
    private boolean directed;
    private Edge<Weight>[][] g;

    public DenseWeightedGraph(int n, boolean directed) {
        assert n > 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;

        g = new Edge[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], null);
        }
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
    public void addEdge(Edge<Weight> e) {
        assert e.v1() >= 0 && e.v1() < n;
        assert e.v2() >= 0 && e.v2() < n;
        if (hasEdge(e.v1(), e.v2()))
            return;

        g[e.v1()][e.v2()] = new Edge<Weight>(e);
        if (e.v1() != e.v2() && !directed) {
            g[e.v2()][e.v1()] = new Edge<Weight>(e.v2(), e.v1(), e.wt());
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert w >= 0 && w < n;
        assert v >= 0 && v < n;
        return g[v][w] != null;
    }

    @Override
    public void show() {

    }

    // 返回图中一个顶点的所有临边
    @Override
    public List<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        List<Edge<Weight>> adjV = Lists.newArrayList();

        for (int i = 0; i < n; i++)
            if (g[v][i] != null)
                adjV.add(g[v][i]);
        return adjV;
    }
}
