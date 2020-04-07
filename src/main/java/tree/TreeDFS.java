package tree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreeDFS {
  //      1
  //    /   \
  //   6      3
  //  /  \     /
  //  5   2    4

  public static void main(String[] args) {
    TreeDFS treeDFS = new TreeDFS();
    TreeNode root = TreeUtils.genTree(new Integer[]{1, 6, 3, 5, 2, 4, null});

//    treeDFS.preOrderIter(root);
//    treeDFS.preOrderR(root);
//    treeDFS.inOrder(root);
//    treeDFS.inOrderR(root);

    treeDFS.postOrder(root);

    // LCA test
//    TreeNode node1 = TreeUtils.getNode(root, 2);
//    TreeNode node2 = TreeUtils.getNode(root, 5);
//    TreeNode lca = getLCA(root, node1, node2);
//    System.out.println(lca.val);
  }

  private void preOrderR(TreeNode crr) {
    if (crr == null) return;
    System.out.println(" > " + crr.val);
    preOrderR(crr.left);
    preOrderR(crr.right);
  }

  private void inOrderR(TreeNode crr) {
    if (crr == null) return;
    inOrderR(crr.left);
    System.out.println(" > " + crr.val);
    inOrderR(crr.right);
  }

  private void preOrder(TreeNode crr) {
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

  private void inOrder(TreeNode crr) {
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

  private void postOrder(TreeNode crr) {
    // 左 右 根 反过来就是 根 右 左
    List<Integer> list = new ArrayList<>();
//    Deque<TreeNode> list = new ArrayList<>();

    Stack<TreeNode> stack = new Stack<>();
    stack.push(crr);
    while (!stack.isEmpty()) {
      crr = stack.pop();
      if (crr == null) continue;
      // 根 右 左
      list.add(crr.val);
      stack.push(crr.left);
      stack.push(crr.right);
    }

    Collections.reverse(list);
    list.forEach(System.out::println);
  }


  // 非 BST 公共祖先 Lowest Common Ancestor of Binary Tree
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

}
