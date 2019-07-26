package leetcode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * @author hw83770
 */
public class LCA235 {

  class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // 递归 BST
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    TreeNode big = p.val > q.val ? p : q;
    TreeNode least = p.val <= q.val ? p : q;
    if (big.val >= root.val && least.val <= root.val)
      return root; // 分别在 root 两侧

    if (big.val < root.val)
      return lowestCommonAncestor(root.left, p, q); // 都在root左子树
    else {
      return lowestCommonAncestor(root.right, p, q); // 都在root 右子树
    }
  }
}
