package leetcode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import tree.TreeNode;

public class PathSum437 {
  public static void main(String[] args) {}
  //
  // root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

  //		  10
  //		 /  \
  //		5   -3
  //		/ \    \
  //		3   2   11
  //		/ \   \
  //		3  -2   1

  int count = 0;

  public int pathSum(TreeNode root, int sum) {

    if (root == null) return 0;
    count = findPath(root, sum);
    count += pathSum(root.left, sum); // 递归自己 left
    count += pathSum(root.right, sum); // 递归自己 right

    return count;
  }

  // 找到以 node 为根的 加起来为sum的 结果个数
  private int findPath(TreeNode node, int sum) {
    if (node == null) return 0;
    int res = 0;
    if (sum == node.val) res += 1; // 不return的原因是后面可能还有路径，因为有负数

    res += findPath(node.left, sum - node.val);
    res += findPath(node.right, sum - node.val);

    return res;
  }
}
