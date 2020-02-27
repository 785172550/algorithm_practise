package tree;


import java.util.Stack;

public class TreeDFS {
  //      1
  //    /   \
  //   6      3
  //  /  \     /
  //  5   2    4

  static TreeNode node6 = new TreeNode(2, null, null);
  static TreeNode node5 = new TreeNode(5, null, null);
  static TreeNode node4 = new TreeNode(4, null, null);
  static TreeNode node3 = new TreeNode(3, node4, null);
  static TreeNode node2 = new TreeNode(6, node5, node6);

  public static TreeNode genTree() {
    return new TreeNode(1, node2, node3);
  }

  // 非 BST 公共祖先
  public static TreeNode getLCA(TreeNode root, TreeNode l, TreeNode r) {
    if (root == null) return null;
    if (root.val == l.val || root.val == r.val) return root;

    TreeNode left = getLCA(root.left, l, r);
    TreeNode right = getLCA(root.right, l, r);

    if (left != null && right != null) return root;
    else if (left != null) return left;
    else if (right != null) return right;
    else return null;
  }

  public static void testLCA() {
    //      1
    //    /   \
    //   6      3
    //  /  \     /
    //  5   2    4
    TreeNode root = genTree();
    TreeNode lca = getLCA(root, node6, node4);
    System.out.println(lca.val);
  }

  public static void main(String[] args) {
    TreeDFS treeDFS = new TreeDFS();
    TreeNode root = genTree();
//    treeDFS.preOrderIter(root);
//    treeDFS.preOrderRecursive(root);

    treeDFS.inOrderIter(root);
    treeDFS.inOrderR(root);

//    testLCA();
  }

  private void preOrderRecursive(TreeNode crr) {
    if (crr == null) return;
    System.out.println(" > " + crr.val);
    preOrderRecursive(crr.left);
    preOrderRecursive(crr.right);
  }

  private void inOrderR(TreeNode crr) {
    if (crr == null) return;
    inOrderR(crr.left);
    System.out.println(" > " + crr.val);
    inOrderR(crr.right);
  }

  private void preOrderIter(TreeNode crr) {
    Stack<TreeNode> stack = new Stack<>();
    stack.push(crr);
    while (!stack.isEmpty()) {
      crr = stack.pop();
      if (crr == null) continue;
      System.out.println(crr.val);
      stack.push(crr.right);
      stack.push(crr.left);
    }
  }

  private void inOrderIter(TreeNode crr) {
    Stack<TreeNode> stack = new Stack<>();
    while (!stack.isEmpty() || crr != null) {
      while (crr != null) {
        stack.push(crr);
        crr = crr.left;
      }
      crr = stack.pop();
      System.out.println(crr.val);
      crr = crr.right;
    }
  }

}
