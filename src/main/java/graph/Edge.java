package graph;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge<Weight>> {
    private int a, b; // 边的两个端点
    private Weight weight; // 边的权值

//    public Edge(int a, int b, Weight weight) {
//
//    }

    public Edge(Edge<Weight> o) {
        this.a = o.v1();
        this.b = o.v2();
        this.weight = o.wt();
    }


    public int v1() {
        return a;
    }

    public int v2() {
        return b;
    }

    public Weight wt() {
        return weight;
    }

    public String toString() {
        return "" + a + '-' + b + ':' + weight;
    }


    // 边之间权值比较
    @Override
    public int compareTo(Edge<Weight> o) {
        return Integer.compare(weight.compareTo(o.weight), 0);
    }
}
