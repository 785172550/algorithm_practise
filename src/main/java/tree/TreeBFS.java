package tree;

import java.util.LinkedList;
import utils.ArrayUtils;

public class TreeBFS {

  public static void main(String[] args) {

    TreeNode root = genTree();
//    int d = depth(root, 0);
//    int dd = depthNR(root);
//    System.out.println("depth " + d + ":" + dd);

//    leftView(root, d);

    printLevel(root, 1);
//    printLevelNR(root, 1);
  }

  private static void levelOrderNR(TreeNode crr) {
    if (crr == null) {
      return;
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    TreeNode current = null;
    queue.offer(crr); // 将根节点入队

    while (!queue.isEmpty()) {
      current = queue.poll(); // 出队队头元素并访问
      System.out.print(current.val + "-->");
      if (current.left != null) // 如果当前节点的左节点不为空入队
      {
        queue.offer(current.left);
      }
      if (current.right != null) // 如果当前节点的右节点不为空，把右节点入队
      {
        queue.offer(current.right);
      }
    }
  }

  private static void levelOrder(TreeNode crr, int depth) {
    if (crr == null)
      return;
    for (int i = 0; i < depth; i++) {
      printLevel(crr, i);
    }
  }

  private static void printLevelNR(TreeNode crr, int level) {
    LinkedList<TreeNode> queue = new LinkedList<>();
    TreeNode current = null;
    queue.offer(crr); // 将根节点入队
    int depth = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      if (level == depth) {
        for (int i = 0; i < size; i++) {
          System.out.println("level:" + queue.poll().val);
        }
        return;
      } else {
        for (int i = 0; i < size; i++) {
          TreeNode node = queue.poll();
          if (node.left != null)
            queue.offer(node.left);
          if (node.right != null)
            queue.offer(node.right);
        }
        depth++;
      }

    }
  }

  private static void printLevel(TreeNode crr, int level) {
    if (crr == null) return;
    if (level == 0) {
      System.out.println(crr.val);
      return;
    }
    printLevel(crr.left, level - 1);
    printLevel(crr.right, level - 1);
  }

  private static int depth(TreeNode crr, int level) {
    if (crr == null)
      return level;

    return Math.max(depth(crr.left, level + 1), depth(crr.right, level + 1));
  }

  private static int depthNR(TreeNode crr) {
    if (crr == null)
      return 0;

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.offer(crr);
    int depth = 0;
    while (!queue.isEmpty()) {
      int width = queue.size();
      depth++;
      for (int i = 0; i < width; i++) {
        crr = queue.poll();
        if (crr.left != null)
          queue.offer(crr.left);
        if (crr.right != null)
          queue.offer(crr.right);
      }
    }
    return depth;

  }

  private static void leftView(TreeNode crr, int depth) {
    int[] res = new int[depth];

    findLeft(crr, 0, res);
    ArrayUtils.printArray(res);
  }

  private static void findLeft(TreeNode crr, int index, int[] res) {
    if (crr == null)
      return;
    if (res[index] == 0)
      res[index] = crr.val;

    findLeft(crr.left, index + 1, res);
    findLeft(crr.right, index + 1, res);
  }

  // 1
  // / \
  // 2 3
  // /\ /
  // 5 6 4

  private static TreeNode genTree() {
    TreeNode node6 = new TreeNode(6, null, null);
    TreeNode node5 = new TreeNode(5, null, null);
    TreeNode node4 = new TreeNode(4, null, null);
    TreeNode node3 = new TreeNode(3, node4, null);
    TreeNode node2 = new TreeNode(2, node5, node6);

    return new TreeNode(1, node2, node3);
  }
}
