package leetcode;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 * <p>
 * 两个条件:
 * 1. 节点的值相同
 * 2. 左子树 于 右子树 镜像对称
 */
public class SymmeticTree101 {
  public boolean isSymmetric(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    if (root == null) return true;
    queue.add(root.left);
    queue.add(root.right);
    while (queue.size() > 1) {
      TreeNode left = queue.poll();
      TreeNode right = queue.poll();
      if (left == null && right == null) continue;
      if (left == null ^ right == null) return false;  // key step
      if (left.val != right.val) return false;

      // 相反添加
      queue.add(left.left);
      queue.add(right.right);

      queue.add(left.right);
      queue.add(right.left);
    }
    return true;
  }

  public boolean isSymmetric2(TreeNode root) {
    return isMinor(root.left, root.right);
  }

  private boolean isMinor(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;
    if (left.val != right.val) return false;

    // 相反添加
    return isMinor(left.left, left.right) && isMinor(right.right, right.left);
  }

}
