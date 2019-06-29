# algorithm_practise
algorithm_practise

### 链表

```
写大于读的情形

LinkedHashMap 与 LRU算法 基于访问时间的链表 或者 基于插入时间的链表

```

哈希表: 

### 数组

基本排序算法
```
插入，归并，快排

递归 和 迭代
```

数组遍历的为了减少时间复杂的的方法

```
对撞指针
滑动窗口
当遇到int[]数组时利用下标index 的关系

```


### 栈和队

队列: 广度优先BFS

栈： 深度优先DFS  

```java


 // 栈 和 递归 
public void preOrder(TreeNode crr) {
	if (crr == null) // 递归首先考虑终止条件
		return;

	System.out.println(crr.getContent());
	preOrder(crr.left);
	preOrder(crr.right);
}
  
  // ###########
while (!stack.isEmpty() || crr != null) { // 循环首先考虑循环体内的迭代操作
	if (crr != null) {
		stack.push(crr);
		System.out.println(crr.getContent());
		crr = crr.left;
	} else {
		TreeNode t = (TreeNode) stack.pop();
		crr = t.getRight();
	}
}

```


### 堆

```
堆 是完全二叉树

堆：大顶堆，每个节点的值大于等于左右子节点的值，构建初始堆 复杂度为O(n,)在交换并重建堆的过程中，
需交换n-1次，而重建堆的过程中，根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn  ???

索引堆

```

### 树

二叉搜索树： 
```
left < current < right  不一定是完全二叉树，所以无法用数组装载
增 比较之后 加到叶子节点，如果遇到相等的情况 可以视为修改
删 Hubbard 删除法， 将该删除节点的子树中最大的节点来替换该节点
改 找到之后 修改

二分搜索树的顺序性：
找到二分搜索树的节点的 pre 和 post 节点
找到在 某个区间 [floor...ceil] 的所有节点

每个节点增加一个值表示，该节点为根的子树的节点数，可以算出树节点的排序

---

前中后序遍历和 
递归和迭代 

树形问题， 二分问题

trie 字典
```

### 并查集

并查集 只判断 节点是否在相同集合 ===(等价于) 节点是否连接， 不判断连接的路径，所以需要的时间复杂度更低。
基本方法： isConnect(), Union()

* quick find
```
最基本版本， 将元素a, b union就是， 将a, b 所在的集合的所有元素parent设为相同。 一次连接时间复杂度O(n)
isConnect() 比较parent相同 O(1)

```

* quick union

```
将元素a, b union就是， 将a, b 的root parent 设为相同。 一次连接时间复杂度和树的层级相关
isConnect() 比较root parent相同 时间复杂度和树的层级相关

优化减小树的层级，路径压缩
(每次 isConnect时， 循环会findParent, 每次find如果parent不为root, parent = parent.parent， 则压缩了一层)

union 的时候比较树的层级

```

### 图

图的表示：
```
稀疏图(sparse Graph, 邻接表adjiacency lists), 
稠密图(dense graph, 邻接矩阵adjiacency matrix)

```

图的算法

```
广度优先遍历与最短路径

深度优先遍历与联通分量

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

递归和树形问题


```


