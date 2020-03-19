package tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreeUtils {

  // 生成树
  public static TreeNode genTree(Integer[] arr) {

    List<TreeNode> treeList = Arrays.stream(arr)
            .map(i -> new TreeNode(i, null, null))
            .collect(Collectors.toList());

    for (int i = 0; i < treeList.size() / 2; i++) {
      TreeNode node = treeList.get(i);
      if (node.val == null) continue;

      node.left = treeList.get(2 * i + 1);
      node.right = treeList.get(2 * i + 2);
      if (node.left.val == null) node.left = null;
      if (node.right.val == null) node.right = null;
    }

    return treeList.get(0);
  }

  public static TreeNode getNode(TreeNode root, int val) {
    if (root == null)
      return null;
    if (root.val == val)
      return root;

    // find left
    TreeNode left = getNode(root.left, val);
    if (left != null) {
      return left;
    }

    // find right
    TreeNode right = getNode(root.right, val);
    if (right != null) {
      return right;
    }
    return null;
  }

  public static void main(String[] args) {
    //    int len = arr.length;
//    //  等比数列求和
////    (1 - Math.pow(2, level)) / (1 - 2) = len
//    int level = (int) log(len + 1, 2);
    System.out.println(log(7 + 1 + 8, 2));
  }

  public static double log(double value, double base) {
    return Math.log(value) / Math.log(base);
  }
}
