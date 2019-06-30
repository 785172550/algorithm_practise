package datastructures.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
