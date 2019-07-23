package unionset;

public class QuickUnion implements DisjointSets {

  private int[] parent;
  private int[] rank; // rank[i]表示以i为根的集合所表示的树的层数

  public QuickUnion(int size) {
    rank = new int[size];
    parent = new int[size];

    // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
    for (int i = 0; i < size; i++) {
      parent[i] = i;
      rank[i] = 1;
    }
  }

  // 查找过程, 查找元素p所对应的集合编号
  // O(h)复杂度, h为树的高度
  @Override
  public int find(int t) {
    assert t >= 0 && t < parent.length;
    // path compression 2, 递归算法
    if (t != parent[t]) parent[t] = find(parent[t]);
    return parent[t];
  }

  public int find2(int p) {
    while (p != parent[p]) {
      // 路径压缩，让当前结点指向自己父亲的父亲
      parent[p] = parent[parent[p]];
      p = parent[p]; // parent[p] 已经等于 parent[parent[p]]
    }
    return p;
  }

  // 合并元素p和元素q所属的集合
  // O(h)复杂度, h为树的高度
  @Override
  public void Union(int a, int b) {
    int aRoot = find(a);
    int bRoot = find(b);
    if (aRoot == bRoot) return;

    // 根据两个元素所在树的rank不同判断合并方向
    // 将rank低的集合合并到rank高的集合上
    if (rank[aRoot] < rank[bRoot]) parent[aRoot] = bRoot; // a 接 b 上
    else if (rank[bRoot] < rank[aRoot]) parent[bRoot] = aRoot;
    else {
      parent[bRoot] = aRoot;
      rank[aRoot] += 1; // 此时, 我维护rank的值
    }
  }

  @Override
  public boolean isConnected(int a, int b) {
    return find(a) == find(b);
  }

  @Override
  public int getSize() {
    return parent.length;
  }
}
