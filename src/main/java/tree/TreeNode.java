package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

  public Integer val;
  public TreeNode left;
  public TreeNode right;

//	public TreeNode(int val, TreeNode left, TreeNode right) {
//		super();
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}

}
