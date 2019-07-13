package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;

//	public TreeNode(int val, TreeNode left, TreeNode right) {
//		super();
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}

}
