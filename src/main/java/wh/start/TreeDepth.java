package wh.start;

import tree.TreeNode;

public class TreeDepth {
  public static void main(String[] args) {
//    T test = new T();
//    TreeNode root;
//    test.levelOrder(root, 0);
  }

  // ebay
  private int depth(TreeNode node, int level) {
    if (node == null)
      return level;
    return Math.max(depth(node.left, level + 1), depth(node.right, level + 1));
  }

  // 最短的深度
  private int minDepth(TreeNode node, int level) {
    if (node.left == null && node.right == null)
      return level;
    else if (node.left == null && node.right != null)
      return minDepth(node.right, level + 1);
    else if (node.right == null && node.left != null)
      return minDepth(node.left, level + 1);
    else {
      return Math.min(minDepth(node.left, level + 1), minDepth(node.right, level + 1));
    }

  }

}
