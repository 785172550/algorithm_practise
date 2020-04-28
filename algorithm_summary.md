
### 一些经典的算法思路和框架

#### 树就是递归
```sbtshell
dfs tree: -> 

def dfs(root):
  out root.val
  if !left:  dfs(root.left)
  if !right: dfs(root.right)

def dfsNR(root):
  stack.push(root, visit=false)
  while(stack not null):
    node,  visit = stack.pop
    if(visit)
      out node.val
    else:
      if !right: stack.push(node.right, visit=false)
      if !left: stack.push(node.left, visit=false)
      out node.val // stack.push(node, visit=true)
      
可以看出 核心思路就是将 递归调用dfs函数的地方, 替换成压栈


---
postOrder 装逼写法, 左右根 reverse -> 根右左
so, 可以在preOrder 根左右 的基础上改下: 

def postOrderNR(root):
  res = []
  stack.push(root)
  
  while(stack not null):
    crr = stack.pop
    if(crr == null): continue
    // 根右左 出栈顺序
    res.append(crr)
    stack.push(crr.left)
    stack.push(crr.right)
    
  // reverse would be 左右根 -> post order
  return res.reverse()


-----------------------------------

树的公共祖先寻找:
首先如果p q 在树中，最坏情况LCA 是root
如果LCA(root.left, p, q) 有值，说明在左边，这就是一个递归结构！
递归终止条件 root.val == p.val || q.val

def LCA(root, p, q):
 if(!root): return null
 if(root.val == p.val || root.val == q.val): 
   return root.val //找到了公共祖先
 
 // 倒着思考，如果left是p, right是q, 
 // 那么公共祖先在两者上面一层, 就是root
 left = LCA(root.left, p, q)
 right = LCA(root.right, p, q)
 if(!left && !right): return root
 
 // 两种情况
 // 1 公共祖先找到了，就是left,所以一直返回 节点到上层
 // 2. 公共祖先没找到，只找到了p或q的值，也要返回一个 非null的值， 
 // 使得当left right 同时非null时，确定root是LCA
 if(!left): return left // right没有值，公共祖先就是自己
 if(!right): return right
 eles return null 

-----------------------------------
 
leveL order:
不管是树还是图，广度优先总是与 最短路径有关(最短子树) 

def bfs(root):
  queue.offer(root)
  int depth = 1
  while(queue):
    size = queue.size
    for [0...size]: // 每一层
        node = queue.poll
        if(!left): queue.offer;
        if(!right): queue.offer;
    depth++
      
```

---

#### 回溯
```sbtshell
回溯就是树状寻路，而且是DFS

// 全排列
list<list> res;
def permute(int[] nums):
  if(!nums) return;
  find_permute(nums, new templist) // 

def find_permute(nums, templist): 
  //定义一个递归函数，递归找第i位的全排列
  if(temlist.size == len(nums))
    res.add(new templist)
  for i in [0...nums]:
    if(nums[i] not in templist)//维护used状态，等价于used[i] = false
      templist.add(nums[i])
      find_permute(nums,  templist)  
      templist.remove(nums[i])
  
```

flood fill 第一滴墨水 扩散

---
#### dp
```sbtshell
dp 要构建 状态转移方程，找到重叠子问题，而且有最优子结构

LCS 公共最长子序列问题
状态转移方程：
LCS(m,n) 表示[0...m] [0...n] 两个字符串的 LCS
if s1[m] == s2[n]: LCS(m,n) = LCS(m-1,n-1) + 1
if s1[m] != s2[n]: LCS(m,n) = max(LCS(m-1,n), LCS(m,n-1))
* 两个参数的状态转移方程 可以放在一个二维数组中思考

-----------------------------------

LIS(最长递增子序列):
LIS(n) 表示[0...n] 的LIS 长度, j: [0...n-1]
LIS(n) = max( 1 + LIS(j): if nums[n] >= nums[j] ) 

-----------------------------------

01背包问题
状态转移方程:
F(n, c)： n 是物品个数， c是背包容积， F是是的价值最大
对于F(i, c) i: [0...n]:
F(i, c) = max(F(i-1, c), v(i) + F(i-1, c-w(i)))

对于第i个物品：要么不放入背包，c不变。要么放入背包加上i的价值，减去i的容量

def knapsack(int[] v, int[] w, int c): 
  // v[i] i的value, w[i]的容积， c背包容积
  beatValue(v, w, c, v.len - 1)

加入记忆
memo[index][c]
  
// index 当前正要放的 index 物品  
def beatValue(int[] v, int[] w, int c, int index):
  if(memo[index][c] != 0) return memo[index][c] // 记忆之前的状态
  
  int value1 = beatValue(v, w, c, index-1)
  int value2 = v[index] + beatValue(v, w, c-w[index], index-1)
  memo[index][c] = max(value1, value2) // 记忆
  return memo[index][c]；
  

```

#### 贪心
局部最优是否可以导致 全局最优？


