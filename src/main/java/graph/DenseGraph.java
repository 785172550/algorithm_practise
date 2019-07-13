package graph;


// 稠密图 邻接矩阵

public class DenseGraph {

    int n; // 节点数
    int m; // 边数

    boolean directed;
    boolean[][] g; // 图的具体数据

    public DenseGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        this.m = 0; // 初始边为0
        this.directed = directed;

        // g 初始为布尔矩阵， g[i][j] = false 表示 i -> j 没有边
        g = new boolean[n][n];
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    // add a edge
    private void addEdge(int v, int w) {
        if (checkVertexRange(v) && checkVertexRange(w)) {
            if (hasEdge(v, w)) {
                return;
            }
            g[v][w] = true;
            if (!directed) {
                g[w][v] = true;
            }
            m++;
        }

    }

    boolean hasEdge(int v, int w) {
        return checkVertexRange(v) && checkVertexRange(w) && g[v][w];
    }

    private boolean checkVertexRange(int v) {
        return v >= 0 && v < n;
    }

}
