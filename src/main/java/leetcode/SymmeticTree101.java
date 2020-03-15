package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import tree.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
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

      queue.add(left.left);
      queue.add(right.right);

      queue.add(left.right);
      queue.add(right.left);
    }
    return true;
  }
}
