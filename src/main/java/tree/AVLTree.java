package tree;

/**
 * 二叉平衡树
 *
 * 树的定义一般都具有递归性质，这是树与递归的关系
 *
 * AVL ：树中每个节点，左子树 《= cur < 右子树， 且左右子树的层高差 <= 1
 *
 * 查询可为 O(NlogN)
 */
public class AVLTree {

  class Node {

    int val;
    Node left;
    Node right;
  }

  private Node root;
  private int size;
}
