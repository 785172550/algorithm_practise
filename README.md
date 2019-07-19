# algorithm_reearch
algorithm_reearch

- [algorithm_reearch](#algorithmreearch)
    - [链表](#%E9%93%BE%E8%A1%A8)
    - [数组](#%E6%95%B0%E7%BB%84)
    - [栈和队](#%E6%A0%88%E5%92%8C%E9%98%9F)
    - [堆](#%E5%A0%86)
    - [树](#%E6%A0%91)
    - [并查集](#%E5%B9%B6%E6%9F%A5%E9%9B%86)
    - [图](#%E5%9B%BE)
    - [基础算法](#%E5%9F%BA%E7%A1%80%E7%AE%97%E6%B3%95)
    - [基础算法思想](#%E5%9F%BA%E7%A1%80%E7%AE%97%E6%B3%95%E6%80%9D%E6%83%B3)
    - [学习问题与笔记](#%E5%AD%A6%E4%B9%A0%E9%97%AE%E9%A2%98%E4%B8%8E%E7%AC%94%E8%AE%B0)

### 链表
leetcode: 146
```
写大于读的情形
LinkedHashMap与 LRU算法 基于访问时间的链表 或者 基于插入时间的链表


```

哈希表:  leetcode: 1

### 数组
leetcode: 

基本排序算法
```
插入，
归并 -> 递归 和 迭代
快排 -> 随机 三路 leetcode75
```
数组遍历的为了减少时间复杂的的方法

```
对撞指针
滑动窗口
当遇到int[]数组时利用下标index 的关系

```

### 栈和队
leetcode:

队列: BFS

栈：DFS  

```java
 // 栈和递归 
public void preOrder(TreeNode crr) {
    if (crr == null) // 递归首先考虑终止条件
        return;
    System.out.println(crr.val);
    preOrder(crr.left);
    preOrder(crr.right);
}
  
  // ###########
while (!stack.isEmpty() || crr != null) { // 循环首先考虑循环体内的迭代操作
    if (crr != null) {
        stack.push(crr);
        System.out.println(crr.val);
        crr = crr.left;
    } else {
        TreeNode t = (TreeNode) stack.pop();
        crr = t.right;
    }
}

// 另外 一种
while (!stack.isEmpty()) {
    crr = stack.pop();
    if (crr == null) continue;
    System.out.println(crr.val);
    stack.push(crr.right);
    stack.push(crr.left);
}

```


### 堆
leetcode:

```
堆 是完全二叉树

堆：大顶堆，每个节点的值大于等于左右子节点的值，构建初始堆 复杂度为O(n,)在交换并重建堆的过程中，
需交换n-1次，而重建堆的过程中，根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn  ???

索引堆

优先队列
leetcode
```

### 树
leetcode: 235 98 450 108 230 236 LCA

* 树的种类
```
二叉搜索树BST:

left < current < right  不一定是完全二叉树，所以无法用数组装载
基本操作：
增 比较之后 加到叶子节点，如果遇到相等的情况 可以视为修改
删 Hubbard 删除法， 将该删除节点的子树中最大的节点来替换该节点
改 找到之后 修改

二分搜索树的顺序性：
找到二分搜索树的节点的 pre 和 post 节点
找到在 某个区间 [floor...ceil] 的所有节点

节点数据结构 增加一个属性表示，该节点为根的子树的节点数，可以算出树节点的顺序
----------------------------

AVL:
基于BST，并且 左右子树高度差 <= 1
rightRotate
  //     node                   x
  //    /   \     右旋转       /  \
  //   x    T2   ------->   y   node
  //  / \                       /  \
  // y  T1                     T1  T2

leftRotate
  //   node                     x
  //  /   \     左旋转         /  \
  // T1   x   --------->   node   T3
  //     / \              /   \
  //    T2 T3            T1   T2

----------------------------

RBTree:
1）每个结点要么是红的，要么是黑的。
2）根结点是黑的。
3）每个叶结点，即空结点（NIL）是黑的。  // 可以忽略
4）如果一个结点是红的，那么它的俩个儿子和父亲都是黑的。
5）对每个结点，从该结点到其子孙结点的所有路径上包含相同数目的黑结点。 // 每个子树 黑高相等

----------------------------
BTree/B+:
矮胖树多叉，适合外排序。充分利用page cache
B+ 数据都在leaf，且leaf节点链表连接

----------------------------
字典树Trie: 前缀匹配

----------------------------
线段树

```
* 树形问题
```
DFS：
前中后序遍历和 
递归和迭代
找root到leaf的路径 

BSF：
层序 
高度
左视图 
每层节点 

树形问题， 二分问题

```

### 并查集
leetcode:

并查集 只判断 节点是否在相同集合===(等价于)节点是否连接，不判断连接的路径，所以需要的时间复杂度更低。
基本方法： isConnect(), Union()

* quick find
```
最基本版本， 将元素a, b union就是， 将a, b 所在的集合的所有元素parent设为相同。

Union()时间复杂度O(n)
isConnect() 比较parent相同 O(1)
```

* quick union

```
将元素a, b union就是， 将a, b 的root parent 设为相同。 

Union() 时间复杂度和树的层级相关 < logN
isConnect() 比较root parent相同 时间复杂度和树的层级相关  < logN

优化减小树的层级，路径压缩
(每次 isConnect时， 循环会findParent, 每次find如果parent不为root, parent = parent.parent， 则压缩了一层)

union 的时候比较树的层级

```

### 图
leetcode:

```
图的表示：
稀疏图(sparse Graph, 邻接表adjiacency lists), 
稠密图(dense graph, 邻接矩阵adjiacency matrix)

图的算法：
广度优先遍历与最短路径
深度优先遍历与联通分量
MST最小生成树：prim 贪心算法

```



### 基础算法
leetcode:

```sbtshell
深度优先，广度优先，二分查找，递归

最小生成树 Prim算法 kruskal 算法
```

### 基础算法思想
```sbtshell
递归 recursive：
leetcode: 
101 SymmeticTree(BFS) 

----------------------------

分治 divide and conquer：
leetcode:
----------------------------

分治， 递归都是基本思想， 会在回溯，贪心，动规之中使用


回溯 back tracking： 相当于一种暴力解法，可以通过减枝优化，排列组合问题常用解法
leetcode: 120(dfs) 47(permutations)
----------------------------

贪心 ：局部最优  vs 全局最优
leetcode:
----------------------------

动态规划dp：递归 + 记录中间过程 = 记忆化搜索(自顶向下) 反向 -> dp (自底向上)
dp key point -> 寻找状态转移方程
01背包
leetcode: 
64 minpath
300 LIS
198 house robber
```


---------
### 学习问题与笔记

```sbtshell
面试思考：
印象深刻的bug
面向对象
设计模式
网络相关， 安全相关， 并发相关， 内存相关问题
系统设计 scalability

过去项目的总结：
犯过的错误
遇到的bug
冲突处理的方式
最享受的工作内容


自己做的项目， 看过的技术书籍的总结，代码整理， 技术博客

准备好合适的问题问面试官： 展示自己的思考
1. 入职之后的具体事宜
2. 小组团队的运行模式， scrum
3. 团队的关于这个项目的目前进展，后续规划 ？
4. 团队产品的某个问题？
5. 团队采用的某些解决方案，某些技术的原因，标准？
6. 我对XXX 很感兴趣， 在这个团队里我会有哪些机会深入了解这个技术？


---

问题：
树形问题
leetcode 练习 235 98 450 108 230 236 LCA
树的遍历，递归找sum值
树的层数

数据库的底层实现离不开平衡二分搜索树？

递归和树形问题?
数据库是否用了二叉搜索树技术?

回溯法一般用于查找一个结果

回溯算法关键 -> 找出问题中的递归模型，或者说是递归调用
回溯算法本质上 其实是一种暴力算法，但是可以对其进行一些优化，例如减枝

递归调用的重要特征 -> 要返回回溯

回溯法backtracking 处理经典的问题 排列问题(permutations)
leetcode练习 47


```


