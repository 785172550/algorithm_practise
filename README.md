# algorithm_practise
algorithm_practise

### 基础数据结构
```sbtshell
链表：写大于读
栈： 深度优先DFS
队列: 广度优先BFS
哈希表: 
图： 稀疏图(sparse Graph, 邻接表adjiacency lists), 稠密图(dense graph, 邻接矩阵adjiacency matrix)
Trie： 字典
并查集 unionSet：适用于判断是否连接的问题 QuickFind vs quick Union， 

```

### 基础数据机构的算法实现
```sbtshell
堆：大顶堆，每个节点的值大于等于左右子节点的值，构建初始堆 复杂度为O(n)，
在交换并重建堆的过程中，需交换n-1次，而重建堆的过程中，根据完全二叉树的性质，
[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn

---

二叉搜索树： left < current < right
不一定是完全二叉树，所以无法用数组装载
增删改查操作
前中后序遍历

---

各种排序算法
```

### 基础算法
```sbtshell
深度优先，广度优先，二分查找，递归

最小生成树 Prim算法 kruskal 算法
```

### 基础算法思想
```sbtshell
递归，分治，回溯，贪心，动态规划
```