package tree;

import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreeDFS {
  public static void main(String[] args) {
    TreeDFS treeDFS = new TreeDFS();
    TreeNode root = treeDFS.genTree();
    treeDFS.preOrderIter(root);
    treeDFS.preOrderRecursive(root);
  }

  @SuppressWarnings("unused")
  private void preOrderRecursive(TreeNode crr) {
    if (crr == null) return;
    log.info(" > " + crr.val);
    preOrderRecursive(crr.left);
    preOrderRecursive(crr.right);
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
