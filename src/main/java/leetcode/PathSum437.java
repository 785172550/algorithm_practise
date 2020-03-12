package leetcode;

import tree.TreeNode;
import tree.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum437 {

  //      1
  //    /   \
  //   6      3
  //  /  \     /
  //  5   2    4

  public static void main(String[] args) {
    TreeNode root = TreeUtils.genTree(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1, null, null, null, null});

    PathSum437 pathSum437 = new PathSum437();
    System.out.println(pathSum437.pathSum(root, 8));

    System.out.println(pathSum437.pathSum2(root, 8));
  }
  // root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

//          10
//         /  \
//         5    -3
//        /  \    \
//        3   2    11
//       / \   \
//      3  -2   1

  int count = 0;

  //  找出路径数量
  public int pathSum(TreeNode root, int sum) {

    if (root == null) return 0;
    count = findPath(root, sum);
    count += pathSum(root.left, sum); // 递归自己 left
    count += pathSum(root.right, sum); // 递归自己 right

    return count;
  }

  // 找到以 node 为根的 加起来为sum的 结果个数
  private int findPath(TreeNode node, int sum) {
    if (node == null) return 0;
    int res = 0;
    if (sum == node.val) res += 1; // 不return的原因是后面可能还有路径，因为有负数

    res += findPath(node.left, sum - node.val);
    res += findPath(node.right, sum - node.val);

    return res;
  }


  private List<List<Integer>> rlist = new LinkedList<>();

  // 找出路径
  public List<List<Integer>> pathSum2(TreeNode root, int sum) {
    if (root == null) return rlist;
    findPath2(root, sum, new ArrayList<>());
    pathSum2(root.left, sum);
    pathSum2(root.right, sum);
    return rlist;
  }

  private int findPath2(TreeNode node, int sum, List<Integer> path) {
    if (node == null) return 0;
    int res = 0;
    if (sum == node.val) {
      path.add(node.val);
      rlist.add(new LinkedList<>(path));
      path.remove(node.val); // 回溯，下面还要在add
    }

    path.add(node.val);
    res += findPath2(node.left, sum - node.val, path);

    res += findPath2(node.right, sum - node.val, path);
    path.remove(node.val);

    return res;
  }
}
