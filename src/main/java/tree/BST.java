package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * binary search tree 二叉搜索树 树的定义一般都具有递归性质，这是树与递归的关系
 *
 * BST：树中每个节点，左子树 <= cur < 右子树
 */
public class BST {

  class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  private TreeNode root;
  private int size;

  public BST() {

    root = null;
    size = 0;
  }

  public void add(int val) {
    root = add(root, val); // 递归调用， 所以需要返回值
    size++;
  }

  private TreeNode add(TreeNode node, int val) {
    if (node == null)
      return new TreeNode(val);
    if (val < node.val)
      node.left = add(node.left, val);
    else if (val > node.val)
      node.right = add(node.right, val);
    return node;
  }

  public void remove(int val) {
    root = remove(root, val);
  }

  private TreeNode remove(TreeNode node, int val) {

    if (node == null)
      return null;
    if (node.val == val) {

      //  左子树空 直接接 右子树
      if (node.left == null) {
        TreeNode right = node.right;
//        node = null;
        size--;
        return right;
      }
      if (node.right == null) {
        TreeNode left = node.left;
        node = null;
        size--;
        return left;
      }

      // 左右都不为空 早右子树最小的 节点替代删除的点
      TreeNode successor = min(node.right);
      successor.right = removeMin(node.right);
      successor.left = node.left;
      node.left = node.right = null;
      return successor;

    }

    if (root.val < val)
      node.left = remove(node.left, val);
    else if (root.val > val)
      node.right = remove(node.right, val);
    return node;
  }

  private TreeNode min(TreeNode node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  private TreeNode removeMin(TreeNode node) {

    if (node.left == null) {
      TreeNode right = node.right;
      node = null;
      size--;
      return right;
    }
    node.left = removeMin(node.left);
    return node;
  }

  // ===================

  // 前序遍历以node为根的二分搜索树, 递归算法
  private void preOrder(TreeNode node) {

    if (node == null)
      return;

    System.out.println(node.val);
    preOrder(node.left);
    preOrder(node.right);
  }

  // 二分搜索树的非递归前序遍历
  public void preOrderNR() {

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pop();
      System.out.println(cur.val);

      if (cur.right != null)
        stack.push(cur.right);
      if (cur.left != null)
        stack.push(cur.left);
    }
  }

  // 中序遍历以node为根的二分搜索树, 递归算法
  private void inOrder(TreeNode node) {

    if (node == null)
      return;

    inOrder(node.left);
    System.out.println(node.val);
    inOrder(node.right);
  }

  private void inOrderNR(TreeNode node) {
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root.right);
    stack.push(root);
    stack.push(root.left);

    while (!stack.isEmpty()) {

      TreeNode cur = stack.pop();
      if (node.left == null)
        System.out.println(node);
      else {
        stack.push(cur.right);
        stack.push(cur);
        stack.push(cur.left);
      }

    }
  }

  // 后序遍历以node为根的二分搜索树, 递归算法
  private void postOrder(TreeNode node) {

    if (node == null)
      return;

    postOrder(node.left);
    postOrder(node.right);
    System.out.println(node.val);
  }

  // 二分搜索树的层序遍历
  public void levelOrder() {

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode cur = q.remove();
      System.out.println(cur.val);

      if (cur.left != null)
        q.add(cur.left);
      if (cur.right != null)
        q.add(cur.right);
    }
  }

  // 寻找二分搜索树的最小元素
  public int minimum() {
    if (size == 0)
      throw new IllegalArgumentException("BST is empty!");

    return min(root).val;
  }

  // 生成以node为根节点，深度为depth的描述二叉树的字符串
  private void generateBSTString(TreeNode node, int depth, StringBuilder res) {

    if (node == null) {
      res.append(generateDepthString(depth) + "null\n");
      return;
    }

    res.append(generateDepthString(depth) + node.val + "\n");
    generateBSTString(node.left, depth + 1, res);
    generateBSTString(node.right, depth + 1, res);
  }

  private String generateDepthString(int depth) {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      res.append("--");
    }
    return res.toString();
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    generateBSTString(root, 0, res);
    return res.toString();
  }


  public static void main(String[] args) {

    int[] arr = new int[]{23, 14, 12, 24, 3, 13, 4};
    BST bst = new BST();
    for (int a : arr) {
      bst.add(a);
    }
    System.out.println(bst);
  }
}
