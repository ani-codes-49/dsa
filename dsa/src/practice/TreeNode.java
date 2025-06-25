package practice;

public class TreeNode {
	TreeNode right;
	TreeNode left;
	int val;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(TreeNode left, TreeNode right, int val) {

		this.right = right;
		this.left = left;
		this.val = val;

	}
}
