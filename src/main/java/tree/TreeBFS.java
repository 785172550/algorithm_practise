package tree;

import java.util.LinkedList;

public class TreeBFS {

  public static void main(String[] args) {}

  private void levelOrder(TreeNode crr) {
    if (crr == null) {
      return;
    }
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
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

  // 1
  // / \
  // 2 3
  // /\ /
  // 5 6 4

  private TreeNode genTree() {
    TreeNode node6 = new TreeNode(6, null, null);
    TreeNode node5 = new TreeNode(5, null, null);
    TreeNode node4 = new TreeNode(4, null, null);
    TreeNode node3 = new TreeNode(3, node4, null);
    TreeNode node2 = new TreeNode(2, node5, node6);

    return new TreeNode(1, node2, node3);
  }
}
