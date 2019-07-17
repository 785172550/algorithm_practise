package tree;

import java.util.TreeMap;

/**
 * 红黑树： 红黑树常为 各种语言的内置的数据结构， 例如java {java.util.TreeMap} 树的定义一般都具有递归性质，这是树与递归的关系
 *
 * 每个节点 要么位红 要么位黑
 *
 *
 * 查询可为 O(NlogN)
 */
public class RBTree {

  TreeMap treeMap;

  class Node {

    int val;
    Node left;
    Node right;
    Node parent;
    boolean isRed;
  }

  private Node root;
  private int size;

}
